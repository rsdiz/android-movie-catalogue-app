package com.xiaofeng.flowlayoutmanager

import android.graphics.Point
import android.graphics.PointF
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.xiaofeng.flowlayoutmanager.cache.CacheHelper
import java.util.*
import kotlin.math.abs

/**
 * Layout manager for flow views. support different view height, support item add/removed notification
 * support align to left/right edge. support scroll/smooth scroll.
 */
class FlowLayoutManager : RecyclerView.LayoutManager() {
    var recyclerView: RecyclerView? = null
    private var firstChildAdapterPosition = 0
    var recyclerRef: Recycler? = null
    private var flowLayoutOptions: FlowLayoutOptions = FlowLayoutOptions()
    private var newFlowLayoutOptions: FlowLayoutOptions = FlowLayoutOptions.clone(flowLayoutOptions)
    var layoutHelper: LayoutHelper? = null
    var cacheHelper: CacheHelper? = null

    private var globalLayoutListener: OnGlobalLayoutListener? = null
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: Recycler, state: RecyclerView.State) {
        if (!cacheHelper!!.valid() && childCount != 0) {
            return
        }
        if (cacheHelper!!.contentAreaWidth() != layoutHelper!!.visibleAreaWidth()) {
            cacheHelper!!.contentAreaWidth(layoutHelper!!.visibleAreaWidth())
        }
        recyclerRef = recycler
        if (state.isPreLayout) {
            onPreLayoutChildren(recycler, state)
        } else {
            cacheHelper!!.startBatchSetting()
            onRealLayoutChildren(recycler)
            cacheHelper!!.endBatchSetting()
        }
    }

    private fun onPreLayoutChildren(recycler: Recycler, state: RecyclerView.State) {

        // start from first view child
        val firstItemAdapterPosition = getChildAdapterPosition(0)
        if (firstItemAdapterPosition == RecyclerView.NO_POSITION) {
            detachAndScrapAttachedViews(recycler)
            return
        }
        var currentItemPosition = if (firstItemAdapterPosition < 0) 0 else firstItemAdapterPosition
        var point =
            layoutHelper!!.layoutStartPoint(LayoutContext.fromLayoutOptions(flowLayoutOptions))
        var x = point.x
        var y = point.y
        var height = 0
        var newline: Boolean
        var realX = point.x
        var realY = point.y
        var realHeight = 0
        var realNewline: Boolean
        val rect = Rect()
        val realRect = Rect()
        // detach all first.
        detachAndScrapAttachedViews(recycler)
        val beforeContext = LayoutContext.fromLayoutOptions(flowLayoutOptions)

        // this option use old options alignment & new options line limit to calc items for animation.
        val afterContext = LayoutContext.clone(beforeContext)
        afterContext.layoutOptions!!.itemsPerLine = newFlowLayoutOptions.itemsPerLine

        // track before removed and after removed layout in same time, to make sure only add items at
        // bottom that visible after item removed.
        while (currentItemPosition < state.itemCount) {
            val child = recycler.getViewForPosition(currentItemPosition)
            val childRemoved = isChildRemoved(child)
            // act as removed view still there, to calc new items location.
            newline = calcChildLayoutRect(child, x, y, height, beforeContext, rect)
            if (newline) {
                point = startNewline(rect, beforeContext)
                x = point.x
                y = point.y
                height = rect.height()
                beforeContext.currentLineItemCount = 1
            } else {
                x = advanceInSameLine(x, rect, beforeContext)
                height = height.coerceAtLeast(rect.height())
                beforeContext.currentLineItemCount++
            }
            if (!childRemoved) {
                realNewline =
                    calcChildLayoutRect(child, realX, realY, realHeight, afterContext, realRect)
                if (realNewline) {
                    point = startNewline(realRect, afterContext)
                    realX = point.x
                    realY = point.y
                    realHeight = realRect.height()
                    afterContext.currentLineItemCount = 1
                } else {
                    realX = advanceInSameLine(realX, realRect, afterContext)
                    realHeight = realHeight.coerceAtLeast(realRect.height())
                    afterContext.currentLineItemCount++
                }
            }

            // stop add new view if after removal, new items are not visible.
            if (!childVisible(
                    realX,
                    realY,
                    realX + rect.width(),
                    realY + rect.height()
                )
            ) {
                recycler.recycleView(child)
                break
            } else {
                if (childRemoved) {
                    addDisappearingView(child)
                } else {
                    addView(child)
                }
                layoutDecorated(child, rect.left, rect.top, rect.right, rect.bottom)
            }
            currentItemPosition++
        }
        flowLayoutOptions = FlowLayoutOptions.clone(newFlowLayoutOptions)
    }

    private fun onRealLayoutChildren(recycler: Recycler) {
        detachAndScrapAttachedViews(recycler)
        val startPoint = layoutStartPoint()
        var x = startPoint.x
        var y = startPoint.y
        val itemCount = itemCount
        var height = 0
        var newLine: Boolean
        val rect = Rect()
        val layoutContext = LayoutContext.fromLayoutOptions(flowLayoutOptions)
        for (i in firstChildAdapterPosition until itemCount) {
            val child = recycler.getViewForPosition(i)
            newLine = calcChildLayoutRect(child, x, y, height, layoutContext, rect)
            if (!childVisible(false, rect)) {
                recycler.recycleView(child)
                return
            } else {
                addView(child)
                layoutDecorated(child, rect.left, rect.top, rect.right, rect.bottom)
                cacheHelper!!.setItem(i, Point(rect.width(), rect.height()))
            }
            if (newLine) {
                val lineInfo = startNewline(rect)
                x = lineInfo.x
                y = lineInfo.y
                height = rect.height()
                layoutContext.currentLineItemCount = 1
            } else {
                x = advanceInSameLine(x, rect, layoutContext)
                height = height.coerceAtLeast(rect.height())
                layoutContext.currentLineItemCount++
            }
        }
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun canScrollVertically(): Boolean {
        if (childCount == 0) {
            return false
        }
        val firstChild = getChildAt(0)
        val lastChild = getChildAt(childCount - 1)
        val topChild = getChildAt(getMaxHeightLayoutPositionInLine(0))
        val bottomChild = getChildAt(
            getMaxHeightLayoutPositionInLine(
                childCount - 1
            )
        )
        var topReached = false
        var bottomReached = false
        if (getChildAdapterPosition(firstChild) == 0) {
            if (getDecoratedTop(topChild!!) >= topVisibleEdge()) {
                topReached = true
            }
        }
        if (getChildAdapterPosition(lastChild) == recyclerView!!.adapter!!.itemCount - 1) {
            if (getDecoratedBottom(bottomChild!!) <= bottomVisibleEdge()) {
                bottomReached = true
            }
        }
        return !(topReached && bottomReached)
    }

    override fun scrollVerticallyBy(dy: Int, recycler: Recycler, state: RecyclerView.State): Int {
        if (dy == 0) {
            return 0
        }
        if (itemCount == 0) {
            return 0
        }
        val firstChild = getChildAt(0)
        val lastChild = getChildAt(childCount - 1)
        val topChild = getChildAt(getMaxHeightLayoutPositionInLine(0))
        val bottomChild = getChildAt(
            getMaxHeightLayoutPositionInLine(
                childCount - 1
            )
        )
        var topReached = false
        var bottomReached = false
        if (getChildAdapterPosition(firstChild) == 0) {
            if (getDecoratedTop(topChild!!) >= topVisibleEdge()) {
                topReached = true
            }
        }
        if (getChildAdapterPosition(lastChild) == recyclerView!!.adapter!!.itemCount - 1) {
            if (getDecoratedBottom(bottomChild!!) <= bottomVisibleEdge()) {
                bottomReached = true
            }
        }
        if (dy > 0 && bottomReached) {
            return 0
        }
        if (dy < 0 && topReached) {
            return 0
        }
        return if (dy > 0) contentMoveUp(dy, recycler) else contentMoveDown(dy, recycler)
    }

    override fun onItemsChanged(recyclerView: RecyclerView) {
        flowLayoutOptions = FlowLayoutOptions.clone(newFlowLayoutOptions)
        if (cacheHelper != null) {
            cacheHelper!!.clear()
        }
        cacheHelper = CacheHelper(flowLayoutOptions.itemsPerLine, layoutHelper!!.visibleAreaWidth())
        super.onItemsChanged(recyclerView)
    }

    override fun onItemsAdded(recyclerView: RecyclerView, positionStart: Int, itemCount: Int) {
        cacheHelper!!.add(positionStart, itemCount)
        super.onItemsAdded(recyclerView, positionStart, itemCount)
    }

    override fun onItemsRemoved(recyclerView: RecyclerView, positionStart: Int, itemCount: Int) {
        cacheHelper!!.remove(positionStart, itemCount)
        super.onItemsRemoved(recyclerView, positionStart, itemCount)
    }

    override fun onItemsUpdated(recyclerView: RecyclerView, positionStart: Int, itemCount: Int) {
        cacheHelper!!.invalidSizes(positionStart, itemCount)
        super.onItemsUpdated(recyclerView, positionStart, itemCount)
    }

    override fun onItemsUpdated(
        recyclerView: RecyclerView,
        positionStart: Int,
        itemCount: Int,
        payload: Any?
    ) {
        cacheHelper!!.invalidSizes(positionStart, itemCount)
        super.onItemsUpdated(recyclerView, positionStart, itemCount, payload)
    }

    override fun onItemsMoved(recyclerView: RecyclerView, from: Int, to: Int, itemCount: Int) {
        cacheHelper!!.move(from, to, itemCount)
        super.onItemsMoved(recyclerView, from, to, itemCount)
    }

    /**
     * Contents moving up to top
     */
    private fun contentMoveUp(dy: Int, recycler: Recycler): Int {
        var actualDy = dy
        var maxHeightIndex = getMaxHeightLayoutPositionInLine(childCount - 1)
        var maxHeightItem = getChildAt(maxHeightIndex)
        var offscreenBottom = getDecoratedBottom(maxHeightItem!!) - bottomVisibleEdge()
        if (offscreenBottom >= dy) {
            offsetChildrenVertical(-dy)
            return dy
        }
        while (getChildAdapterPosition(childCount - 1) < itemCount - 1) {
            addNewLineAtBottom(recycler)
            maxHeightIndex = getMaxHeightLayoutPositionInLine(childCount - 1)
            maxHeightItem = getChildAt(maxHeightIndex)
            offscreenBottom += getDecoratedMeasuredHeight(maxHeightItem!!)
            if (offscreenBottom >= dy) {
                break
            }
        }
        if (offscreenBottom < dy) {
            actualDy = offscreenBottom
        }
        offsetChildrenVertical(-actualDy)
        while (!lineVisible(0)) {
            recycleLine(0, recycler)
        }
        firstChildAdapterPosition = getChildAdapterPosition(0)
        return actualDy
    }

    /**
     * Contents move down to bottom
     */
    private fun contentMoveDown(dy: Int, recycler: Recycler): Int {
        var actualDy = dy
        var maxHeightItemIndex = getMaxHeightLayoutPositionInLine(0)
        var maxHeightItem = getChildAt(maxHeightItemIndex)

        // scrolling
        var offScreenTop = topVisibleEdge() - getDecoratedTop(maxHeightItem!!)
        if (offScreenTop > abs(actualDy)) {
            offsetChildrenVertical(-dy)
            return dy
        }
        while (getChildAdapterPosition(0) > 0) {
            addNewLineAtTop(recycler)
            maxHeightItemIndex = getMaxHeightLayoutPositionInLine(0)
            maxHeightItem = getChildAt(maxHeightItemIndex)
            offScreenTop += getDecoratedMeasuredHeight(maxHeightItem!!)
            if (offScreenTop >= abs(dy)) {
                break
            }
        }
        if (offScreenTop < abs(dy)) {
            actualDy = -offScreenTop
        }
        offsetChildrenVertical(-actualDy)
        while (!lineVisible(childCount - 1)) {
            recycleLine(childCount - 1, recycler)
        }
        firstChildAdapterPosition = getChildAdapterPosition(0)
        return actualDy
    }

    /**
     * Add new line of elements at top, to keep layout, have to virtually layout from beginning.
     * Here is an example to explain why: Say total width is 10, current line is [5, 2, 1], 3 numbers
     * before current line is 7, 2, 6. If you just look back, you could say previous line [2, 6], but there
     * is another possibility: [7, 2], [6]. You don't know which one is correct without knowing how the line
     * before previous one, same thing could happen to that line too, so you have to sort everything from beginning
     * or cache the result.
     */
    private fun addNewLineAtTop(recycler: Recycler) {
        var x = layoutStartPoint().x
        val bottom = getDecoratedTop(getChildAt(getMaxHeightLayoutPositionInLine(0))!!)
        val y: Int
        var height = 0
        val lineChildren: MutableList<View> = LinkedList()
        var currentAdapterPosition = 0
        val endAdapterPosition = getChildAdapterPosition(0) - 1
        val rect = Rect()
        var newline: Boolean
        var firstItem = true
        var layoutContext = LayoutContext.fromLayoutOptions(flowLayoutOptions)
        val firstItemAdapterPosition = getChildAdapterPosition(0)
        if (cacheHelper!!.hasPreviousLineCached(firstItemAdapterPosition)) {
            val previousLineIndex = cacheHelper!!.itemLineIndex(firstItemAdapterPosition) - 1
            val previousLine = cacheHelper!!.getLine(previousLineIndex)
            val firstNewItemAdapterPosition = cacheHelper!!.firstItemIndex(previousLineIndex)
            for (i in 0 until previousLine!!.itemCount) {
                val newView = recycler.getViewForPosition(firstNewItemAdapterPosition + i)
                addView(newView, i)
                lineChildren.add(newView)
            }
            height = previousLine.maxHeight
        } else {
            while (currentAdapterPosition <= endAdapterPosition) {
                val newChild = recycler.getViewForPosition(currentAdapterPosition)
                newline = calcChildLayoutRect(newChild, x, 0, height, layoutContext, rect)
                cacheHelper!!.setItem(currentAdapterPosition, Point(rect.width(), rect.height()))
                // add view to make sure not be recycled.
                addView(newChild, lineChildren.size)
                if (newline && !firstItem) {
                    // end of one line, but not reach the top line yet. recycle the line and
                    // move on to next.
                    for (viewToRecycle in lineChildren) {
                        removeAndRecycleView(viewToRecycle, recycler)
                    }
                    lineChildren.clear()
                    x = advanceInSameLine(layoutStartPoint().x, rect, layoutContext)
                    height = rect.height()
                    layoutContext.currentLineItemCount = 1
                } else {
                    x = advanceInSameLine(x, rect, layoutContext)
                    height = height.coerceAtLeast(rect.height())
                    firstItem = false
                    layoutContext.currentLineItemCount++
                }
                lineChildren.add(newChild)
                currentAdapterPosition++
            }
        }
        x = layoutStartPoint().x
        y = bottom - height
        firstItem = true
        layoutContext = LayoutContext.fromLayoutOptions(flowLayoutOptions)
        for (i in lineChildren.indices) {
            val childView = lineChildren[i]
            newline = calcChildLayoutRect(childView, x, y, height, layoutContext, rect)
            if (newline && firstItem) {
                val rectHeight = rect.height()
                rect.top -= rectHeight
                rect.bottom -= rectHeight
                firstItem = false
            }
            layoutDecorated(childView, rect.left, rect.top, rect.right, rect.bottom)
            x = advanceInSameLine(x, rect, layoutContext)
        }
    }

    /**
     * Add new line at bottom of views.
     */
    private fun addNewLineAtBottom(recycler: Recycler) {
        var x = layoutStartPoint().x
        val y = getDecoratedBottom(getChildAt(getMaxHeightLayoutPositionInLine(childCount - 1))!!)
        var childAdapterPosition = getChildAdapterPosition(childCount - 1) + 1
        // no item to add
        if (childAdapterPosition == itemCount) {
            return
        }
        val rect = Rect()
        var newline: Boolean
        var firstItem = true
        val layoutContext = LayoutContext.fromLayoutOptions(flowLayoutOptions)
        while (childAdapterPosition < itemCount) {
            val newChild = recycler.getViewForPosition(childAdapterPosition)
            newline = calcChildLayoutRect(newChild, x, y, 0, layoutContext, rect)
            cacheHelper!!.setItem(childAdapterPosition, Point(rect.width(), rect.height()))
            if (newline && !firstItem) {
                recycler.recycleView(newChild)
                layoutContext.currentLineItemCount = 1
                return
            } else {
                addView(newChild)
                layoutDecorated(newChild, rect.left, rect.top, rect.right, rect.bottom)
                x = advanceInSameLine(x, rect, layoutContext)
                childAdapterPosition++
                firstItem = false
                layoutContext.currentLineItemCount++
            }
        }
    }

    override fun onAttachedToWindow(view: RecyclerView) {
        super.onAttachedToWindow(view)
        recyclerView = view
        layoutHelper = LayoutHelper(this, recyclerView!!)
        cacheHelper = CacheHelper(flowLayoutOptions.itemsPerLine, layoutHelper!!.visibleAreaWidth())
        if (layoutHelper!!.visibleAreaWidth() == 0) {
            if (globalLayoutListener == null) {
                globalLayoutListener = object : OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        globalLayoutListener = null
                        cacheHelper!!.contentAreaWidth(layoutHelper!!.visibleAreaWidth())
                    }
                }
            }
            view.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        }
    }

    override fun onDetachedFromWindow(view: RecyclerView, recycler: Recycler) {
        super.onDetachedFromWindow(view, recycler)
        if (globalLayoutListener != null) {
            view.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
            globalLayoutListener = null
        }
    }

    override fun supportsPredictiveItemAnimations(): Boolean {
        return true
    }

    override fun scrollToPosition(position: Int) {
        firstChildAdapterPosition = position
        requestLayout()
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        val smoothScroller: SmoothScroller = object : LinearSmoothScroller(recyclerView.context) {
            override fun computeScrollVectorForPosition(targetPosition: Int): PointF {
                return PointF(0F, getOffsetOfItemToFirstChild(targetPosition, recyclerRef).toFloat())
            }
        }
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    private fun leftVisibleEdge(): Int {
        return paddingLeft
    }

    private fun rightVisibleEdge(): Int {
        return width - paddingRight
    }

    private fun topVisibleEdge(): Int {
        return paddingTop
    }

    private fun bottomVisibleEdge(): Int {
        return height - paddingBottom
    }

    private fun childVisible(
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ): Boolean {
        return if (recyclerView!!.layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            true
        } else Rect.intersects(
            Rect(leftVisibleEdge(), topVisibleEdge(), rightVisibleEdge(), bottomVisibleEdge()),
            Rect(left, top, right, bottom)
        )
    }

    private fun childVisible(preLayout: Boolean, childRect: Rect): Boolean {
        return if (!preLayout && recyclerView!!.layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            true
        } else Rect.intersects(
            Rect(
                leftVisibleEdge(),
                topVisibleEdge(),
                rightVisibleEdge(),
                bottomVisibleEdge()
            ),
            childRect
        )
    }

    private fun getMaxHeightLayoutPositionInLine(layoutPosition: Int): Int {
        val child = getChildAt(layoutPosition)
        var maxIndexBefore = layoutPosition
        var maxIndexAfter = layoutPosition
        var maxHeightBefore = getDecoratedMeasuredHeight(child!!)
        var maxHeightAfter = getDecoratedMeasuredHeight(child)
        var currentIndex = layoutPosition
        val layoutContext = LayoutContext.fromLayoutOptions(flowLayoutOptions)
        while (currentIndex >= 0 && !isStartOfLine(currentIndex, layoutContext)) {
            val beforeChild = getChildAt(currentIndex)
            if (getDecoratedMeasuredHeight(beforeChild!!) > maxHeightBefore) {
                maxIndexBefore = currentIndex
                maxHeightBefore = getDecoratedMeasuredHeight(beforeChild)
            }
            currentIndex--
        }
        // count in first one in line
        if (maxHeightBefore < getDecoratedMeasuredHeight(getChildAt(currentIndex)!!)) {
            maxIndexBefore = currentIndex
            maxHeightBefore = getDecoratedMeasuredHeight(getChildAt(currentIndex)!!)
        }
        currentIndex = layoutPosition
        while (currentIndex < childCount && !isEndOfLine(currentIndex, layoutContext)) {
            val afterChild = getChildAt(currentIndex)
            if (getDecoratedMeasuredHeight(afterChild!!) > maxHeightAfter) {
                maxIndexAfter = currentIndex
                maxHeightAfter = getDecoratedMeasuredHeight(afterChild)
            }
            currentIndex++
        }
        // count in last one in line
        if (maxHeightAfter < getDecoratedMeasuredHeight(getChildAt(currentIndex)!!)) {
            maxIndexAfter = currentIndex
            maxHeightAfter = getDecoratedMeasuredHeight(getChildAt(currentIndex)!!)
        }
        return if (maxHeightBefore >= maxHeightAfter) {
            maxIndexBefore
        } else maxIndexAfter
    }

    private fun getAllViewsInLine(index: Int): List<View?> {
        var firstItemIndex = index
        while (!isStartOfLine(firstItemIndex)) {
            firstItemIndex--
        }
        val viewList: MutableList<View?> = LinkedList()
        viewList.add(getChildAt(firstItemIndex))
        var nextItemIndex = firstItemIndex + 1
        val layoutContext = LayoutContext.fromLayoutOptions(flowLayoutOptions)
        while (nextItemIndex < childCount && !isStartOfLine(nextItemIndex, layoutContext)) {
            viewList.add(getChildAt(nextItemIndex))
            nextItemIndex++
        }
        return viewList
    }

    private fun getChildAdapterPosition(layoutPosition: Int): Int = getChildAdapterPosition(getChildAt(layoutPosition))

    private fun getChildAdapterPosition(child: View?): Int {
        return if (child == null) {
            RecyclerView.NO_POSITION
        } else (child.layoutParams as RecyclerView.LayoutParams).absoluteAdapterPosition
    }

    fun getChildLayoutPosition(child: View): Int = (child.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition

    private fun lineVisible(index: Int): Boolean {
        val maxHeightItemIndex = getMaxHeightLayoutPositionInLine(index)
        val maxHeightItem = getChildAt(maxHeightItemIndex)
        return Rect.intersects(
            Rect(leftVisibleEdge(), topVisibleEdge(), rightVisibleEdge(), bottomVisibleEdge()),
            Rect(
                leftVisibleEdge(),
                getDecoratedTop(maxHeightItem!!),
                rightVisibleEdge(),
                getDecoratedBottom(
                    maxHeightItem
                )
            )
        )
    }

    private fun recycleLine(index: Int, recycler: Recycler) {
        val viewList = getAllViewsInLine(index)
        for (viewToRecycle in viewList) {
            removeAndRecycleView(viewToRecycle!!, recycler)
        }
    }

    private fun getOffsetOfItemToFirstChild(adapterPosition: Int, recycler: Recycler?): Int {
        val firstChildPosition = getChildAdapterPosition(0)
        if (firstChildPosition == adapterPosition) {
            // first child is target, just make sure it is fully visible.
            return topVisibleEdge() - getDecoratedTop(getChildAt(0)!!)
        }
        return if (adapterPosition > firstChildPosition) {
            val lastChildAdapterPosition = getChildAdapterPosition(childCount - 1)
            // target child in screen, no need to calc.
            if (lastChildAdapterPosition >= adapterPosition) {
                val targetChildIndex = childCount - 1 - (lastChildAdapterPosition - adapterPosition)
                getDecoratedTop(getChildAt(targetChildIndex)!!) - topVisibleEdge()
            } else {
                // target child is below screen edge
                var y = getDecoratedBottom(
                    getChildAt(
                        getMaxHeightLayoutPositionInLine(
                            childCount - 1
                        )
                    )!!
                ) - topVisibleEdge()
                var targetAdapterPosition = lastChildAdapterPosition + 1
                var x = layoutStartPoint().x
                var height = 0
                val rect = Rect()
                var newline: Boolean
                val layoutContext = LayoutContext.fromLayoutOptions(flowLayoutOptions)
                while (targetAdapterPosition != adapterPosition) {
                    val nextChild = recycler!!.getViewForPosition(targetAdapterPosition)
                    newline = calcChildLayoutRect(nextChild, x, y, height, layoutContext, rect)
                    if (newline) {
                        x = advanceInSameLine(layoutStartPoint().x, rect, layoutContext)
                        y = rect.top
                        height = rect.height()
                        layoutContext.currentLineItemCount = 1
                    } else {
                        x = advanceInSameLine(x, rect, layoutContext)
                        height = height.coerceAtLeast(getDecoratedMeasuredHeight(nextChild))
                        layoutContext.currentLineItemCount++
                    }
                    recycler.recycleView(nextChild)
                    targetAdapterPosition++
                }
                y
            }
        } else {
            // target is off screen top, Need to start from beginning in data set
            var targetAdapterPosition = 0
            var x = layoutStartPoint().x
            var height = 0
            var y = topVisibleEdge() - getDecoratedTop(getChildAt(0)!!)
            val rect = Rect()
            var newline: Boolean
            val layoutContext = LayoutContext.fromLayoutOptions(flowLayoutOptions)
            while (targetAdapterPosition <= firstChildPosition) {
                val child = recycler!!.getViewForPosition(targetAdapterPosition)
                newline = calcChildLayoutRect(child, x, y, height, rect)
                if (newline) {
                    x = advanceInSameLine(layoutStartPoint().x, rect)
                    height = rect.height()
                    if (targetAdapterPosition >= adapterPosition) {
                        y += height
                    }
                    layoutContext.currentLineItemCount = 1
                } else {
                    x = advanceInSameLine(x, rect)
                    height = height.coerceAtLeast(getDecoratedMeasuredHeight(child))
                    layoutContext.currentLineItemCount++
                }
                targetAdapterPosition++
            }
            -y
        }
    }

    /**
     * Is child has been marked as removed.
     */
    private fun isChildRemoved(child: View): Boolean {
        return (child.layoutParams as RecyclerView.LayoutParams).isItemRemoved
    }

    fun setAlignment(alignment: Alignment?): FlowLayoutManager {
        newFlowLayoutOptions.alignment = alignment!!
        return this
    }

    fun singleItemPerLine(): FlowLayoutManager {
        if (layoutHelper == null) {
            flowLayoutOptions.itemsPerLine = 1
        } else {
            newFlowLayoutOptions.itemsPerLine = 1
        }
        if (cacheHelper != null) {
            cacheHelper!!.clear()
        }
        if (layoutHelper != null) {
            cacheHelper = CacheHelper(1, layoutHelper!!.visibleAreaWidth())
        }
        return this
    }

    fun maxItemsPerLine(itemsPerLine: Int): FlowLayoutManager {
        if (layoutHelper == null) {
            flowLayoutOptions.itemsPerLine = itemsPerLine
        } else {
            newFlowLayoutOptions.itemsPerLine = itemsPerLine
        }
        if (cacheHelper != null) {
            cacheHelper!!.clear()
        }
        if (layoutHelper != null) {
            cacheHelper = CacheHelper(itemsPerLine, layoutHelper!!.visibleAreaWidth())
        }
        return this
    }

    fun removeItemPerLineLimit(): FlowLayoutManager {
        if (layoutHelper == null) {
            flowLayoutOptions.itemsPerLine = FlowLayoutOptions.ITEM_PER_LINE_NO_LIMIT
        } else {
            newFlowLayoutOptions.itemsPerLine = FlowLayoutOptions.ITEM_PER_LINE_NO_LIMIT
        }
        if (cacheHelper != null) {
            cacheHelper!!.clear()
        }
        if (layoutHelper != null) {
            cacheHelper = CacheHelper(
                FlowLayoutOptions.ITEM_PER_LINE_NO_LIMIT,
                layoutHelper!!.visibleAreaWidth()
            )
        }
        return this
    }

    /*****************alignment related functions */
    private fun calcChildLayoutRect(
        child: View,
        x: Int,
        y: Int,
        lineHeight: Int,
        rect: Rect
    ): Boolean {
        return calcChildLayoutRect(
            child,
            x,
            y,
            lineHeight,
            LayoutContext.fromLayoutOptions(flowLayoutOptions),
            rect
        )
    }

    private fun calcChildLayoutRect(
        child: View,
        x: Int,
        y: Int,
        lineHeight: Int,
        layoutContext: LayoutContext,
        rect: Rect
    ): Boolean {
        val newLine: Boolean
        measureChildWithMargins(child, 0, 0)
        val childWidth = getDecoratedMeasuredWidth(child)
        val childHeight = getDecoratedMeasuredHeight(child)
        when (layoutContext.layoutOptions!!.alignment) {
            Alignment.RIGHT -> if (LayoutHelper.shouldStartNewline(
                    x,
                    childWidth,
                    leftVisibleEdge(),
                    rightVisibleEdge(),
                    layoutContext
                )
            ) {
                newLine = true
                rect.left = rightVisibleEdge() - childWidth
                rect.top = y + lineHeight
                rect.right = rightVisibleEdge()
                rect.bottom = rect.top + childHeight
            } else {
                newLine = false
                rect.left = x - childWidth
                rect.top = y
                rect.right = x
                rect.bottom = rect.top + childHeight
            }
            Alignment.LEFT -> if (LayoutHelper.shouldStartNewline(
                    x,
                    childWidth,
                    leftVisibleEdge(),
                    rightVisibleEdge(),
                    layoutContext
                )
            ) {
                newLine = true
                rect.left = leftVisibleEdge()
                rect.top = y + lineHeight
                rect.right = rect.left + childWidth
                rect.bottom = rect.top + childHeight
            } else {
                newLine = false
                rect.left = x
                rect.top = y
                rect.right = rect.left + childWidth
                rect.bottom = rect.top + childHeight
            }
            else -> if (LayoutHelper.shouldStartNewline(
                    x,
                    childWidth,
                    leftVisibleEdge(),
                    rightVisibleEdge(),
                    layoutContext
                )
            ) {
                newLine = true
                rect.left = leftVisibleEdge()
                rect.top = y + lineHeight
                rect.right = rect.left + childWidth
                rect.bottom = rect.top + childHeight
            } else {
                newLine = false
                rect.left = x
                rect.top = y
                rect.right = rect.left + childWidth
                rect.bottom = rect.top + childHeight
            }
        }
        return newLine
    }

    private fun startNewline(
        rect: Rect,
        layoutContext: LayoutContext = LayoutContext.fromLayoutOptions(
            flowLayoutOptions
        )
    ): Point {
        return when (layoutContext.layoutOptions!!.alignment) {
            Alignment.RIGHT -> Point(rightVisibleEdge() - rect.width(), rect.top)
            Alignment.LEFT -> Point(leftVisibleEdge() + rect.width(), rect.top)
            else -> Point(leftVisibleEdge() + rect.width(), rect.top)
        }
    }

    private fun advanceInSameLine(
        x: Int,
        rect: Rect,
        layoutContext: LayoutContext = LayoutContext.fromLayoutOptions(
            flowLayoutOptions
        )
    ): Int {
        return when (layoutContext.layoutOptions!!.alignment) {
            Alignment.RIGHT -> x - rect.width()
            Alignment.LEFT -> x + rect.width()
            else -> x + rect.width()
        }
    }

    private fun layoutStartPoint(): Point {
        return layoutHelper!!.layoutStartPoint(LayoutContext.fromLayoutOptions(flowLayoutOptions))
    }

    private fun isStartOfLine(index: Int): Boolean {
        return isStartOfLine(index, LayoutContext.fromLayoutOptions(flowLayoutOptions))
    }

    private fun isStartOfLine(index: Int, layoutContext: LayoutContext): Boolean {
        return if (index == 0) {
            true
        } else {
            when (layoutContext.layoutOptions!!.alignment) {
                Alignment.RIGHT -> getDecoratedRight(
                    getChildAt(index)!!
                ) >= rightVisibleEdge()
                Alignment.LEFT -> getDecoratedLeft(getChildAt(index)!!) <= leftVisibleEdge()
                else -> getDecoratedLeft(getChildAt(index)!!) <= leftVisibleEdge()
            }
        }
    }

    private fun isEndOfLine(index: Int): Boolean {
        return isEndOfLine(index, LayoutContext.fromLayoutOptions(flowLayoutOptions))
    }

    private fun isEndOfLine(index: Int, layoutContext: LayoutContext): Boolean {
        if (LayoutHelper.hasItemsPerLineLimit(layoutContext.layoutOptions!!) && layoutContext.currentLineItemCount == layoutContext.layoutOptions!!.itemsPerLine) {
            return true
        }
        return if (childCount == 0 || index == childCount - 1) {
            true
        } else isStartOfLine(index + 1, layoutContext)
    }

    companion object {
        private const val LOG_TAG = "FlowLayoutManager"
    }
}

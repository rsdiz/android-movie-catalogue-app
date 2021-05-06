package com.xiaofeng.flowlayoutmanager.cache

import android.graphics.Point
import android.util.SparseArray

/**
 * A Helper class that will save Line information (items count, total width etc.) and will be used
 * for layout, that will avoid low efficiency layout
 */
class CacheHelper(
    /**
     * Item per line limit, set to 0 if no limit
     */
    private val itemPerLine: Int,
    /**
     * The area width for content layout, must be greater than zero
     */
    private var contentAreaWidth: Int
) {
    private var sizeMap: SparseArray<Point> = SparseArray()
    private var lineMap: SparseArray<Line> = SparseArray()
    private var batchSetting = false

    /**
     * Add measured items into cache
     */
    fun add(startIndex: Int, vararg sizes: Point) {
        if (!valid()) {
            return
        }
        invalidateLineMapAfter(startIndex)
        makeSpace(startIndex, sizes.size)
        var index = startIndex
        for (size in sizes) {
            sizeMap.put(index++, size)
        }
        refreshLineMap()
    }

    /**
     * Make space for {@param count} items
     */
    fun add(startIndex: Int, count: Int) {
        if (!valid()) {
            return
        }
        invalidateLineMapAfter(startIndex)
        makeSpace(startIndex, count)
        refreshLineMap()
    }

    /**
     * Sizes has been changed and new sizes not available yet, just remove them from cache
     * The line map will also be invalidate after the invalidated items
     * @param index
     * @param count
     */
    fun invalidSizes(index: Int, count: Int) {
        if (!valid()) {
            return
        }
        invalidateLineMapAfter(index)
        val actualCount = actualCount(index, count)
        for (i in 0 until actualCount) {
            sizeMap.remove(index + i)
        }
        refreshLineMap()
    }

    fun remove(index: Int, count: Int) {
        if (!valid()) {
            return
        }
        invalidateLineMapAfter(index)
        val actualCount = actualCount(index, count)
        for (i in 0 until actualCount) {
            sizeMap.remove(index + i)
        }

        // move everything behind to fill the hole.
        for (i in index + actualCount until sizeMap.size() + actualCount) {
            val tmp = sizeMap[i]
            sizeMap.remove(i)
            sizeMap.put(i - actualCount, tmp)
        }
        refreshLineMap()
    }

    fun setItem(index: Int, newSize: Point) {
        if (!valid()) {
            return
        }
        if (sizeMap[index, null] != null) {
            val cachedPoint = sizeMap[index]
            if (cachedPoint != newSize) {
                invalidateLineMapAfter(index)
                sizeMap.put(index, newSize)
                refreshLineMap()
            }
        } else {
            invalidateLineMapAfter(index)
            sizeMap.put(index, newSize)
            refreshLineMap()
        }
    }

    /**
     * Move items from one place to another. no check on parameter as invoker will make sure it is correct
     */
    fun move(from: Int, to: Int, count: Int) {
        if (!valid()) {
            return
        }
        invalidateLineMapAfter(Math.min(from, to))
        val itemsToMove = arrayOfNulls<Point>(count)
        for (i in from until from + count) {
            itemsToMove[i - from] = sizeMap[i]
        }
        val movingForward = from - to > 0
        var itemsToShift = Math.abs(from - to)
        if (!movingForward) {
            itemsToShift -= count
        }
        var shiftIndex = if (movingForward) from - 1 else from + count
        val shiftIndexStep = if (movingForward) -1 else 1
        var shifted = 0
        while (shifted < itemsToShift) {
            sizeMap.put(shiftIndex - shiftIndexStep * count, sizeMap[shiftIndex])
            shiftIndex += shiftIndexStep
            shifted++
        }
        var setIndex = to
        if (!movingForward) {
            setIndex = from + itemsToShift
        }
        for (item in itemsToMove) {
            sizeMap.put(setIndex++, item)
        }
        refreshLineMap()
    }

    fun getLineMap(): IntArray {
        if (!valid()) {
            return IntArray(0)
        }
        val lineCounts = IntArray(lineMap.size())
        for (i in 0 until lineMap.size()) {
            lineCounts[i] = lineMap[i].itemCount
        }
        return lineCounts
    }

    fun itemLineIndex(itemIndex: Int): Int {
        if (!valid()) {
            return NOT_FOUND
        }
        var itemCount = 0
        for (i in 0 until lineMap.size()) {
            itemCount += lineMap[i].itemCount
            if (itemCount >= itemIndex + 1) {
                return i
            }
        }
        return NOT_FOUND
    }

    private fun containingLine(itemIndex: Int): Line? {
        return if (!valid()) {
            null
        } else getLine(itemLineIndex(itemIndex))
    }

    fun firstItemIndex(lineIndex: Int): Int {
        if (!valid()) {
            return NOT_FOUND
        }
        var itemCount = 0
        for (i in 0 until lineIndex) {
            itemCount += lineMap[i].itemCount
        }
        return itemCount
    }

    fun getLine(lineIndex: Int): Line? {
        return if (!valid()) {
            null
        } else lineMap[lineIndex, null]
    }

    fun hasPreviousLineCached(itemIndex: Int): Boolean {
        if (!valid()) {
            return false
        }
        val lineIndex = itemLineIndex(itemIndex)
        if (lineIndex == NOT_FOUND) {
            return false
        }
        return lineIndex > 0
    }

    fun hasNextLineCached(itemIndex: Int): Boolean {
        if (!valid()) {
            return false
        }
        val lineIndex = itemLineIndex(itemIndex)
        return if (lineIndex == NOT_FOUND) {
            false
        } else !lineMap[lineIndex + 1, Line.EMPTY_LINE].equals(Line.EMPTY_LINE)
    }

    fun clear() {
        sizeMap.clear()
        lineMap.clear()
    }

    fun contentAreaWidth(width: Int) {
        contentAreaWidth = width
        lineMap.clear()
        refreshLineMap()
    }

    fun contentAreaWidth(): Int = contentAreaWidth

    fun valid(): Boolean = contentAreaWidth > 0

    fun startBatchSetting() {
        batchSetting = true
    }

    fun endBatchSetting() {
        batchSetting = false
        lineMap.clear()
        refreshLineMap()
    }
    // ===================== Helper methods ========================
    /**
     * Move item after startIndex to make {count} space(s)
     */
    private fun makeSpace(startIndex: Int, count: Int) {
        for (i in sizeMap.size() - 1 downTo startIndex) {
            sizeMap.put(i + count, sizeMap[i])
        }
        for (i in startIndex until startIndex + count) {
            sizeMap.remove(i)
        }
    }

    /**
     * Rebuild line map. and should stop if there is a hole (like item changed or item inserted but not measured)
     */
    private fun refreshLineMap() {
        if (!valid() || batchSetting) {
            return
        }
        var index = refreshLineMapStartIndex()
        var cachedSize = sizeMap[index, null]
        var lineIndex = lineMap.size()
        var lineItemCount = 0
        var currentLine = containingLine(index)
        if (currentLine == null) {
            currentLine = Line()
        } else {
            lineIndex = itemLineIndex(index)
        }
        var lineWidth = currentLine.totalWidth
        while (cachedSize != null) {
            lineWidth += cachedSize.x
            lineItemCount++
            if (lineWidth <= contentAreaWidth) {
                if (itemPerLine > 0) { // have item per line limit
                    if (lineItemCount > itemPerLine) { // exceed item per line limit
                        lineMap.put(lineIndex, currentLine)

                        // put this item to next line
                        currentLine = Line()
                        addToLine(currentLine, cachedSize, index)
                        lineIndex++
                        lineWidth = cachedSize.x
                        lineItemCount = 1
                    } else {
                        addToLine(currentLine, cachedSize, index)
                    }
                } else {
                    addToLine(currentLine, cachedSize, index)
                }
            } else { // too wide to add this item, put line item count to index and put this one to new line
                lineMap.put(lineIndex, currentLine)
                currentLine = Line()
                addToLine(currentLine, cachedSize, index)
                lineIndex++
                lineWidth = cachedSize.x
                lineItemCount = 1
            }
            index++
            cachedSize = sizeMap[index, null]
        }
        if (currentLine!!.itemCount > 0) {
            lineMap.append(lineIndex, currentLine)
        }
    }

    /**
     * Add view info to line
     */
    private fun addToLine(line: Line?, item: Point, index: Int) {
        line!!.itemCount++
        line.totalWidth += item.x
        line.maxHeight = if (item.y > line.maxHeight) item.y else line.maxHeight
        if (item.y == line.maxHeight) {
            line.maxHeightIndex = index
        }
    }

    /**
     * return actual count from index to expected count or end of sizeMap
     */
    private fun actualCount(index: Int, count: Int): Int = if (index + count > sizeMap.size()) sizeMap.size() - index else count

    /**
     * Invalidate line map that contains item and all lines after
     * @param itemIndex
     */
    private fun invalidateLineMapAfter(itemIndex: Int) {
        if (batchSetting) {
            return
        }
        var itemLineIndex = itemLineIndex(itemIndex)
        var line = lineMap[itemLineIndex, null]
        if (line == null && lineMap.size() > 0) {
            lineMap.remove(lineMap.size() - 1)
        }
        while (line != null) {
            lineMap.remove(itemLineIndex)
            itemLineIndex++
            line = lineMap[itemLineIndex, null]
        }
    }

    private fun refreshLineMapStartIndex(): Int {
        var itemCount = 0
        for (i in 0 until lineMap.size()) {
            itemCount += lineMap[i].itemCount
        }
        return if (itemCount >= sizeMap.size()) {
            NOT_FOUND
        } else itemCount
    }

    companion object {
        const val NOT_FOUND = -1
    }
}

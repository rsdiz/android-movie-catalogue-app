package com.xiaofeng.flowlayoutmanager

import android.graphics.Point
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by xhan on 4/11/16.
 */
class LayoutHelper(var layoutManager: RecyclerView.LayoutManager, var recyclerView: RecyclerView) {
    private fun leftVisibleEdge(): Int = recyclerView.paddingLeft

    private fun rightVisibleEdge(): Int = layoutManager.width - layoutManager.paddingRight

    fun visibleAreaWidth(): Int = rightVisibleEdge() - leftVisibleEdge()

    private fun topVisibleEdge(): Int = layoutManager.paddingTop

    fun bottomVisibleEdge(): Int = layoutManager.height - layoutManager.paddingBottom

    fun layoutStartPoint(layoutContext: LayoutContext): Point {
        return when (layoutContext.layoutOptions?.alignment) {
            Alignment.RIGHT -> Point(rightVisibleEdge(), topVisibleEdge())
            else -> Point(leftVisibleEdge(), topVisibleEdge())
        }
    }

    companion object {
        fun hasItemsPerLineLimit(layoutOptions: FlowLayoutOptions): Boolean = layoutOptions.itemsPerLine > 0

        fun shouldStartNewline(
            x: Int,
            childWidth: Int,
            leftEdge: Int,
            rightEdge: Int,
            layoutContext: LayoutContext
        ): Boolean {
            return if (layoutContext.layoutOptions?.let { hasItemsPerLineLimit(it) } == true && layoutContext.currentLineItemCount === layoutContext.layoutOptions?.itemsPerLine) {
                true
            } else when (layoutContext.layoutOptions?.alignment) {
                Alignment.RIGHT -> x - childWidth < leftEdge
                Alignment.LEFT -> x + childWidth > rightEdge
                else -> x + childWidth > rightEdge
            }
        }
    }
}

package com.xiaofeng.flowlayoutmanager

/**
 * Created by xhan on 4/11/16.
 */
class FlowLayoutOptions {
    var alignment = Alignment.LEFT
    var itemsPerLine = ITEM_PER_LINE_NO_LIMIT

    companion object {
        const val ITEM_PER_LINE_NO_LIMIT = 0
        fun clone(layoutOptions: FlowLayoutOptions): FlowLayoutOptions {
            val result = FlowLayoutOptions()
            result.alignment = layoutOptions.alignment
            result.itemsPerLine = layoutOptions.itemsPerLine
            return result
        }
    }
}

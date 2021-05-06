package com.xiaofeng.flowlayoutmanager

/**
 * Created by xhan on 4/11/16.
 */
class LayoutContext {
    var layoutOptions: FlowLayoutOptions? = null
    var currentLineItemCount = 0

    companion object {
        fun clone(layoutContext: LayoutContext): LayoutContext {
            val resultContext = LayoutContext()
            resultContext.currentLineItemCount = layoutContext.currentLineItemCount
            resultContext.layoutOptions = layoutContext.layoutOptions?.let {
                FlowLayoutOptions.clone(
                    it
                )
            }
            return resultContext
        }

        fun fromLayoutOptions(layoutOptions: FlowLayoutOptions?): LayoutContext {
            val layoutContext = LayoutContext()
            layoutContext.layoutOptions = layoutOptions
            return layoutContext
        }
    }
}

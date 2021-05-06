package com.xiaofeng.flowlayoutmanager.cache

/**
 * Created by xhan on 4/28/16.
 */
class Line {
    var itemCount = 0
    var totalWidth = 0
    var maxHeight = 0
    var maxHeightIndex: Int = -1

    fun clone(): Line {
        val clone = Line()
        clone.itemCount = itemCount
        clone.totalWidth = totalWidth
        clone.maxHeight = maxHeight
        clone.maxHeightIndex = maxHeightIndex
        return clone
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val line = other as Line
        if (itemCount != line.itemCount) return false
        if (totalWidth != line.totalWidth) return false
        return if (maxHeight != line.maxHeight) false else maxHeightIndex == line.maxHeightIndex
    }

    override fun hashCode(): Int {
        var result = itemCount
        result = 31 * result + totalWidth
        result = 31 * result + maxHeight
        result = 31 * result + maxHeightIndex
        return result
    }

    override fun toString(): String {
        return "Line{" +
            "itemCount=" + itemCount +
            ", totalWidth=" + totalWidth +
            ", maxHeight=" + maxHeight +
            ", maxHeightIndex=" + maxHeightIndex +
            '}'
    }

    companion object {
        val EMPTY_LINE = Line()
    }
}

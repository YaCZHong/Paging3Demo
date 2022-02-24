package com.czh.paging3demo.ui.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * 相关博客学习：https://blog.csdn.net/briblue/article/details/70161917
 */
class MyDecoration : RecyclerView.ItemDecoration() {

    private val mDividerHeight: Int = 2
    private val mPaint: Paint = Paint()

    init {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.parseColor("#F5F5F5")
    }

    /**
     * getItemOffsets 是针对每一个 ItemView
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = mDividerHeight
        }
    }

    /**
     * onDraw 方法是针对 RecyclerView 本身
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight

        // 遍历当前屏幕所有可见的 itemView
        repeat(parent.childCount) { index ->
            // 获取当前屏幕可见的第 index 个 itemView
            val child = parent.getChildAt(index)

            // 获取 itemView 在列表中真实的位置
            val adapterPosition = parent.getChildAdapterPosition(child)
            if (adapterPosition != 0) {
                val dividerTop = child.top - mDividerHeight
                val dividerBottom = child.top
                c.drawRect(
                    dividerLeft.toFloat(),
                    dividerTop.toFloat(),
                    dividerRight.toFloat(),
                    dividerBottom.toFloat(),
                    mPaint
                )
            }
        }
    }
}
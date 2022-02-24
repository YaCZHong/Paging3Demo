package com.czh.paging3demo.ui.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

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
        repeat(parent.childCount) { index ->
            val child = parent.getChildAt(index)
            val adapterPosition = parent.getChildAdapterPosition(child)
            if (adapterPosition != 0) {
                val dividerTop = child.top - mDividerHeight
                val dividerLeft = parent.paddingLeft
                val dividerBottom = child.top
                val dividerRight = parent.width - parent.paddingRight
                c.drawRect(
                    dividerLeft.toFloat(),
                    dividerTop.toFloat(),
                    dividerRight.toFloat(),
                    dividerBottom.toFloat(), mPaint
                )
            }
        }
    }
}
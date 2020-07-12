package com.example.ulink.timetable

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import androidx.recyclerview.widget.RecyclerView

class TimeTableListDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.top = dptopx(10).toInt()
    }

    fun dptopx(dp: Int): Float {
        val metrics = context.resources.displayMetrics
        return dp * ((metrics.densityDpi.toFloat()) / DisplayMetrics.DENSITY_DEFAULT)
    }
}
package com.mawla.marvelcharacterkotlinapp.CustomViews

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView

class CustomListView(context: Context?, attrs: AttributeSet?) : ListView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(1000000000, MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}
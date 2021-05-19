package com.example.damaskkebab.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.util.jar.Attributes

class DKTextViewBold(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    init {
        applyFont(context)
    }

    private fun applyFont(context: Context) {
        val typeface: Typeface = Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
        setTypeface(typeface)
    }
}
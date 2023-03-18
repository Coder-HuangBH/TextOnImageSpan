package com.hbh.textonimagespan

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.style.ImageSpan
import androidx.annotation.DrawableRes
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt


class TextOnImageSpan : ImageSpan {

    private var mDrawable : Drawable? = null
    private val mTextPaint: Paint = Paint()
    private var mTextHeight = 0f
    private var mBaseLineOffset = 0f
    var mOffsetX = 0f
    var mOffsetY = 0f

    constructor(context: Context, bitmap : Bitmap) : super(context, bitmap)

    constructor(context: Context, bitmap : Bitmap, verticalAlignment : Int)
            : super(context, bitmap, verticalAlignment)

    constructor(draw : Drawable) : super(draw)

    constructor(draw : Drawable, verticalAlignment : Int) : super(draw, verticalAlignment)

    constructor(context: Context, uri : Uri) : super(context, uri)

    constructor(context: Context, uri : Uri, verticalAlignment : Int)
            : super(context, uri, verticalAlignment)

    constructor(context: Context, @DrawableRes resourceId: Int) : super(context, resourceId)

    constructor(context: Context, @DrawableRes resourceId: Int, verticalAlignment : Int)
            : super(context, resourceId, verticalAlignment)

    init {
        mTextPaint.color = Color.WHITE
        mTextPaint.textSize = 20f
        computerTextParams()
    }

    private fun computerTextParams() {
        val fm = mTextPaint.fontMetrics
        mTextHeight = fm.descent - fm.ascent
        mBaseLineOffset = abs(fm.ascent)
    }

    fun setTextSize(size: Float) {
        mTextPaint.textSize = size
        computerTextParams()
    }

    fun setTextColor(color: Int) {
        mTextPaint.color = color
    }

    fun setFakeBoldText(bold: Boolean) {
        mTextPaint.isFakeBoldText = bold
    }

    fun setImageHeight(height : Int) {
        drawable?.run {
            if (bounds.height() != height) {
                val scale = height.toFloat() / bounds.height()
                setBounds(bounds.left, bounds.top,
                    bounds.left + (bounds.width() * scale).roundToInt(), bounds.top + height)
            }
            mDrawable = this
        }
    }

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int,
                         fm: Paint.FontMetricsInt?): Int {

        val d = (mDrawable ?: drawable) ?: return 0

        val rect = d.bounds

        fm?.run {
            ascent = -(max(mTextHeight.toInt(), rect.bottom))
            descent = 0
            top = ascent
            bottom = 0
        }

        var size = rect.right

        text?.takeIf { it.isNotEmpty() }?.let {
            size = max(size, (mTextPaint.measureText(text, start, end)).roundToInt())
        }

        return size
    }

    override fun getDrawable(): Drawable? {
        return mDrawable ?: super.getDrawable()
    }

    override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int,
        x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {

        val draw = (mDrawable ?: drawable) ?: return

        canvas.save()

        val totalWidth = draw.bounds.width().toFloat()
        val textWidth = mTextPaint.measureText(text, start, end)

        var transX = x
        if (textWidth > draw.bounds.width()) {
            transX += (textWidth - draw.bounds.width()) / 2
        }
        var transY = bottom - draw.bounds.bottom
        if (mVerticalAlignment == ALIGN_BASELINE) {
            transY -= paint.fontMetricsInt.descent
        } else if (mVerticalAlignment == ALIGN_CENTER) {
            transY = top + (bottom - top) / 2 - draw.bounds.height() / 2
        }
        if (mTextHeight > draw.bounds.height()) {
            canvas.translate(0f, -(mTextHeight - draw.bounds.height()) / 2)
        }
        canvas.translate(transX, transY.toFloat())

        //drawImage
        draw.draw(canvas)

        //drawText
        text?.takeIf { it.isNotEmpty() }?.let {
            val startX = (totalWidth - textWidth) / 2 + mOffsetX

            val mTextBaseLine = (draw.bounds.height() - mTextHeight) / 2 + mBaseLineOffset
            val startY = mTextBaseLine + mOffsetY

            canvas.drawText(text, start, end, startX, startY, mTextPaint)
        }

        canvas.restore()
    }

}
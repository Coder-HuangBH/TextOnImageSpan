package com.hbh.textonimagespan

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.DynamicDrawableSpan.ALIGN_BOTTOM
import android.text.style.DynamicDrawableSpan.ALIGN_CENTER
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val textStr1 = "你的未来不是别人的梦想，而是你自己的努力。"
    val textStr2 = "人生就像一场旅行，不在于目的地，而在于沿途的风景。"
    val textStr3 = "人生没有彩排"
    val textStr4 = "，每一天都是现场直播"
    val textStr5 = "，所以请珍惜。"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTv1Span()
    }

    private fun setTv1Span() {
        val ssb = SpannableStringBuilder()
        var span = TextOnImageSpan(this, R.drawable.location, ALIGN_CENTER)
        span.setFakeBoldText(true)
        span.setTextColor(Color.BLUE)
        span.setTextSize(DisplayUtil.sp2px(this, 16F).toFloat())
        span.setImageHeight(DisplayUtil.dip2px(this, 50f))
        span.mOffsetX = DisplayUtil.dip2px(this, 15f).toFloat()
        span.mOffsetY = DisplayUtil.dip2px(this, 15f).toFloat()
        ssb.append("定位", span, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        ssb.append(textStr1)

        span = TextOnImageSpan(this, R.drawable.sing)
        span.setFakeBoldText(true)
        span.setTextColor(Color.BLACK)
        span.setTextSize(DisplayUtil.sp2px(this, 16F).toFloat())
        span.setImageHeight(DisplayUtil.dip2px(this, 30f))
        span.mOffsetY = DisplayUtil.dip2px(this, -8f).toFloat()
        ssb.append("KTV", span, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        ssb.append(textStr2)

        span = TextOnImageSpan(this, R.drawable.msg)
        span.setFakeBoldText(true)
        span.setTextColor(Color.BLACK)
        span.setTextSize(DisplayUtil.sp2px(this, 24F).toFloat())
        span.setImageHeight(DisplayUtil.dip2px(this, 20f))
        ssb.append(textStr3, span, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        ssb.append(textStr4)

        span = TextOnImageSpan(this, R.drawable.movie)
        span.setFakeBoldText(true)
        span.setTextColor(Color.DKGRAY)
        span.setTextSize(DisplayUtil.sp2px(this, 50F).toFloat())
        span.setImageHeight(DisplayUtil.dip2px(this, 20f))
        ssb.append("电影", span, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        ssb.append(textStr5)

        span = TextOnImageSpan(this, R.drawable.camera, ALIGN_BOTTOM)
        span.setFakeBoldText(true)
        span.setTextColor(Color.RED)
        span.setTextSize(DisplayUtil.sp2px(this, 10F).toFloat())
        span.setImageHeight(DisplayUtil.dip2px(this, 16f))
        ssb.append("相机", span, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        findViewById<TextView>(R.id.tv1).text = ssb
    }

}
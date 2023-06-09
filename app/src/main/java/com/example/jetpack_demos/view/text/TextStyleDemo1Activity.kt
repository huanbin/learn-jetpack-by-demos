package com.example.jetpack_demos.view.text

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.R

/**
 * Style--->整个TextView的所有文本的样式
 * Span-->跨度，一段范围内的文本样式,比如单个字符、段落样式，
 * 这其中又涉及到不可变文本（只是改变外观，尺寸不变，不用触发layout过程）和可变文本（不仅是外观，文本都可能改变，比如文本尺寸、内容等）
 * 如果只改变外观，文本内容不改变，使用textView.setText(spannable, TextView.BufferType.SPANNABLE)，
 * 通过textView.getText得到的文本是Spannable，此时可直接操作Spannable，添加或删除Span，可以复用Spannable，更为高效。如果只是更新Span，
 * Spanned
 * Spannable
 * Editable
 *
 * SpannedString-----》文本和样式都不改变 Spanned
 * SpannableString---》文本不改变，样式改变 Spannable
 * SpannableStringBuilder---》文本和样式都改变Spannable Editable
 */
class TextStyleDemo1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_style_demo1)

        val textView = findViewById<TextView>(R.id.tvView)/*val spannable = SpannableString("Text is spantastic!")
        spannable.setSpan(
            ForegroundColorSpan(Color.RED), 8, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            StyleSpan(BOLD), 8, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        // TextView.BufferType.NORMAL,这个每次都需要设置文本，会触发测量、布局过程
        //textView.text = spannable

        //TextView.BufferType.SPANNABLE,指定文本作为SPANNABLE text存储，通过TextView getText()就能得到Spannable
        textView.setText(spannable, TextView.BufferType.SPANNABLE)
        val textSpannable =
            textView.text as Spannable //SpannableString
        flags其实不是表示是否包含satrt和end 索引位置，而是指satrt和end索引位置插入字符时是否包含，包含则应用样式，否则不会应用样式
        textSpannable.setSpan(
            BackgroundColorSpan(Color.GREEN), 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )*/


        val spannableStringBuilder = SpannableStringBuilder("01234")

        val foregroundColorSpan = ForegroundColorSpan(Color.RED)
        //索引是左闭右开
        spannableStringBuilder.setSpan(
            foregroundColorSpan, 1, 3, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        //Spannable.SPAN_INCLUSIVE_INCLUSIVE 在索引1位置插入文本，发现xx也有foregroundColorSpan样式，在索引3位置插入文本，也会有foregroundColorSpan样式
        spannableStringBuilder.insert(1, "xx")
        textView.text = spannableStringBuilder

        //进程内和进程间传递text with span
        //只有框架内的ParcelableSpan能够在进程内或进程间传递
        //TextUtils.writeToParcel()


//        textView.setSpannableFactory()

        //自定义Span
        /*val spannable = SpannableString("Text is\nspantastic")
        spannable.setSpan(
            QuoteSpan(Color.BLUE),
            8, spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(MyCustomSpan(Color.RED, 2f), 1, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        textView.text = spannable*/

        //Annotation的用法
        /* val spannableString = getText(R.string.span_string) as SpannedString
         val spans = spannableString.getSpans(0, spannableString.length, Annotation::class.java)
         Log.d("Hello", "spans ---> ${spans.size}")
         textView.text = spannableString*/
    }
}

class MyCustomSpan(val color: Int, proportion: Float) : RelativeSizeSpan(proportion) {
    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = color
    }

    override fun updateMeasureState(ds: TextPaint) {
        super.updateMeasureState(ds)
    }
}
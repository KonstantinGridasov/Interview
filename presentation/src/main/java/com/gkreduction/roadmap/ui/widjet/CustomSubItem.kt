package com.gkreduction.roadmap.ui.widjet

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.gkreduction.roadmap.R

@SuppressLint("CustomViewStyleable")
class CustomSubItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var image: ImageView? = null
    private var text: TextView? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.subitem_view, this)
        var textSubItem: String? = null
        var iconSubItem: Drawable? = null
        if (attrs != null) {
            val a = getContext().obtainStyledAttributes(attrs, R.styleable.SubItem)
            iconSubItem = a.getDrawable(R.styleable.SubItem_icon_sub_item)
            textSubItem = a.getString(R.styleable.SubItem_text_sub_item)
            a.recycle()
        }

        image = findViewById(R.id.image_sub_item)

        if (iconSubItem == null)
            image?.visibility = GONE
        else {
            image?.visibility = VISIBLE
            image?.setImageDrawable(iconSubItem)
        }

        text = findViewById(R.id.text_sub_item)
        text?.text = textSubItem

    }

    fun setImageByDestination(destinationId: Int) {
        image?.visibility = when (destinationId) {
            R.id.categoryFragment -> VISIBLE
            else -> GONE
        }
    }


}
package com.gkreduction.roadmap.ui.widjet

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.gkreduction.roadmap.R

@SuppressLint("CustomViewStyleable")
class CustomToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var image: ImageView? = null
    private var textToolBar: TextView? = null
    private var onItemClickListener = {}


    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_view, this)
        image = findViewById(R.id.toolbar_back)
        textToolBar = findViewById(R.id.toolbar_text)
        image?.setOnClickListener { onItemClickListener.invoke() }
    }

    fun setImageByDestination(destinationId: Int) {
        image?.visibility = when (destinationId) {
            R.id.categoryFragment -> VISIBLE
            else -> GONE
        }
        setVisibilityToolbar(destinationId)
    }

    fun setVisibilityToolbar(destinationId: Int) {
        when (destinationId) {
            R.id.homeFragment -> this.visibility = GONE
        }
    }


    fun setOnImageClickListener(function: () -> Unit) {
        this.onItemClickListener = function
    }

    fun setTextName(name: String) {
        textToolBar?.text = name
    }


}
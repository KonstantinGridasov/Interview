package com.gkreduction.interview.ui.main.category.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("itemsCategory", "listenerCategory", requireAll = false)
    fun setListener(
        recyclerView: RecyclerView,
        items: List<String>?,
        clickItem: AdapterCategory.ListenerList?
    ) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter =
                AdapterCategory(
                    it,
                    clickItem
                )
        }
    }

}
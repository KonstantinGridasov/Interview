package com.gkreduction.interview.ui.main.question.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.interview.entity.DataInfo

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("itemsList", "listenerList", "typeList", requireAll = false)
    fun setListener(
        recyclerView: RecyclerView,
        items: List<DataInfo>?,
        clickItem: ListAdapter.ListenerList?,
        typeList: String
    ) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = ListAdapter(it, clickItem, typeList)
        }
    }

}
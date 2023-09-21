package com.gkreduction.roadmap.ui.main.fragment.home.adapter

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.domain.entity.Roadmap

object BindingRoadmapAdapter {
    @JvmStatic
    @BindingAdapter("items_roadmap", requireAll = false)
    fun bindingRoadmapAdapter(
        recyclerView: RecyclerView,
        items: List<Roadmap>?,
    ) {
//        recyclerView.setHasFixedSize(true)
        val adapter = RoadmapAdapter()
        recyclerView.adapter = adapter
        items?.let {
            adapter.updateItems(it)
        }
    }

}
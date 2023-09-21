package com.gkreduction.roadmap.ui.main.fragment.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.ItemRoadmapBinding

class RoadmapAdapter : RecyclerView.Adapter<RoadmapAdapter.ViewHolder>() {
    private var items: List<Roadmap> = emptyList()
    private var onRoadmapClick: ((id: Long) -> Unit)? = null
    private var onTheoryClick: ((id: Long) -> Unit)? = null
    private var onQuestionClick: ((id: Long) -> Unit)? = null

    inner class ViewHolder(val binding: ItemRoadmapBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRoadmapBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_roadmap, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleRoadmap.text = items[position].name

        holder.binding.subItemRoadmap.setOnClickListener { onRoadmapClick?.invoke(items[position].id) }
        holder.binding.subItemTheory.setOnClickListener { onTheoryClick?.invoke(items[position].id) }
        holder.binding.subItemQuestion.setOnClickListener { onQuestionClick?.invoke(items[position].id) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(list: List<Roadmap>) {
        this.items = list
        notifyDataSetChanged()
    }


}
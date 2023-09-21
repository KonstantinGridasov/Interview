package com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter

import android.annotation.SuppressLint
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.entity.Section
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.ItemSectionBinding

class SectionAdapter(
    var onSectionListener: OnSectionListener?,
    var onTopicListener: OnTopicListener,
    var onSubtopicListener: OnSubtopicListener
) : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {
    private var items: List<Section> = emptyList()

    inner class ViewHolder(val binding: ItemSectionBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemSectionBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_section, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleSection.text = getTopicText(position)
        holder.binding.titleSection.setOnClickListener { onSectionListener?.onSectionClick(items[position]) }
        holder.binding.rvTopics.adapter =
            TopicAdapter(items[position].topics, onTopicListener, onSubtopicListener)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(roadmap: Roadmap) {
        this.items = roadmap.section
        notifyDataSetChanged()
    }

    private fun getTopicText(position: Int): Spanned {
        return Html.fromHtml("@" + items[position].name, Html.FROM_HTML_MODE_LEGACY)
    }

}
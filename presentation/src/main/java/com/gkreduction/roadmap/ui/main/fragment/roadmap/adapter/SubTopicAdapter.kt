package com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter

import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.domain.entity.Subtopic
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.ItemSubtopicBinding

class SubTopicAdapter(
    var items: List<Subtopic>,
    var onSubtopicListener: OnSubtopicListener?
) :
    RecyclerView.Adapter<SubTopicAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemSubtopicBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemSubtopicBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_subtopic, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleSubtopic.text = getSubTopicText(position)
        holder.binding.titleSubtopic.setOnClickListener { onSubtopicListener?.onSubtopicClick(items[position].id) }
    }


    private fun getSubTopicText(position: Int): Spanned {
        return Html.fromHtml(
            "<font color='#F1720E'>subtopic </font>" + items[position].name,
            Html.FROM_HTML_MODE_LEGACY
        )
    }

}
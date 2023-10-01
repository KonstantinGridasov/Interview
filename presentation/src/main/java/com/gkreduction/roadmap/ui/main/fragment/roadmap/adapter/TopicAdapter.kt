package com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter

import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.domain.entity.Topic
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.ItemTopicBinding

class TopicAdapter(
    var items: List<Topic>,
    var onTopicListener: OnTopicListener?,
    var onSubtopicListener: OnSubtopicListener?
) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemTopicBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTopicBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_topic, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleTopic.text = getTopicText(position)
        holder.binding.titleTopic.setOnClickListener { onTopicListener?.onTopicClick(items[position]) }
        holder.binding.titleTopic.setOnLongClickListener {
            onTopicListener?.onTopicLongClick(items[position])
            true
        }
        holder.binding.rvSubtopics.adapter =
            SubTopicAdapter(items[position].subtopics, onSubtopicListener)
    }


    private fun getTopicText(position: Int): Spanned {
        return Html.fromHtml(
            "<font color='#F1720E'>topic </font>" + items[position].name,
            Html.FROM_HTML_MODE_LEGACY
        )
    }
}
package com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter

import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.domain.entity.ItemRoadmap
import com.gkreduction.domain.entity.TypeItem
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.ItemSectionBinding
import com.gkreduction.roadmap.databinding.ItemSubtopicBinding
import com.gkreduction.roadmap.databinding.ItemTopicBinding

class SectionAdapter(
    var clickListener: (ItemRoadmap) -> Unit,
    var longListener: (ItemRoadmap) -> Unit,
    var items: List<ItemRoadmap>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolderSection(val binding: ItemSectionBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ViewHolderSubTopic(val binding: ItemSubtopicBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ViewHolderTopic(val binding: ItemTopicBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding: ItemSectionBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_section, parent, false)
                return ViewHolderSection(binding)
            }
            1 -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding: ItemSubtopicBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_subtopic, parent, false)
                return ViewHolderSubTopic(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding: ItemTopicBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_topic, parent, false)
                return ViewHolderTopic(binding)
            }
        }

    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items[position].type) {
            TypeItem.SECTION -> {

                (holder as ViewHolderSection).binding.titleSection.text = getSectionText(position)
                holder.binding.viewBottom.visibility = if (position == 0)
                    GONE else VISIBLE

                holder.binding.titleSection.setOnClickListener {
                    clickListener.invoke(items[position])

                }
                holder.binding.titleSection.setOnLongClickListener {
                    longListener.invoke(items[position])
                    true
                }
            }
            TypeItem.SUBTOPIC -> {
                (holder as ViewHolderSubTopic).binding.titleSubtopic.text =
                    getSubTopicText(position)
                holder.binding.titleSubtopic.setOnClickListener {
                    clickListener.invoke(items[position])
                }
                holder.binding.titleSubtopic.setOnLongClickListener {
                    longListener.invoke(items[position])
                    true
                }
            }
            TypeItem.TOPIC -> {
                (holder as ViewHolderTopic).binding.titleTopic.text = getTopicText(position)
                holder.binding.titleTopic.setOnClickListener { clickListener.invoke(items[position]) }
                holder.binding.titleTopic.setOnLongClickListener {
                    longListener.invoke(items[position])
                    true
                }
            }
            else -> {}
        }


    }


    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            TypeItem.SECTION -> 0
            TypeItem.SUBTOPIC -> 1
            TypeItem.TOPIC -> 2
            else -> -1
        }
    }

    private fun getSectionText(position: Int): Spanned {
        return Html.fromHtml("@" + items[position].name, Html.FROM_HTML_MODE_LEGACY)
    }

    private fun getSubTopicText(position: Int): Spanned {
        return Html.fromHtml(
            "<font color='#F1720E'>subtopic </font>" + items[position].name,
            Html.FROM_HTML_MODE_LEGACY
        )
    }

    private fun getTopicText(position: Int): Spanned {
        return Html.fromHtml(
            "<font color='#F1720E'>topic </font>" + items[position].name,
            Html.FROM_HTML_MODE_LEGACY
        )
    }


}
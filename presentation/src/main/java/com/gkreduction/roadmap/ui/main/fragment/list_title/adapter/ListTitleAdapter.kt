package com.gkreduction.roadmap.ui.main.fragment.list_title.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.ItemListTitleBinding

class ListTitleAdapter(
    var onTitleItemListener: OnTitleItemListener?
) : RecyclerView.Adapter<ListTitleAdapter.ViewHolder>() {
    private var items: List<QuestionAnswer> = emptyList()

    inner class ViewHolder(val binding: ItemListTitleBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemListTitleBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_list_title, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textQuestion.text = items[position].question
        holder.itemView.setOnClickListener { onTitleItemListener?.onTitleClick(items[position].id) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<QuestionAnswer>) {
        this.items = items
        notifyDataSetChanged()
    }


}
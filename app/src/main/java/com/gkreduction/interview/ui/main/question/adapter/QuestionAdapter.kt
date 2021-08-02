package com.gkreduction.interview.ui.main.question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.ItemCategoryBinding
import com.gkreduction.interview.entity.Question
import kotlinx.android.synthetic.main.item_list.view.*

class QuestionAdapter(
    private var items: List<Question>,
    private val listener: ListenerList?
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    interface ListenerList {
        fun onItemClick(id: Int)
    }

    inner class ViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { listener!!.onItemClick(items[adapterPosition].id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCategoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textData.text = items[position].question
        holder.binding.executePendingBindings()

    }
}
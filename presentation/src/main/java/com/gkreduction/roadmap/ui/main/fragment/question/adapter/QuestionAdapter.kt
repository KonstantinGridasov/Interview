package com.gkreduction.roadmap.ui.main.fragment.question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.ItemQuestionBinding
import com.gkreduction.roadmap.entity.Question

class QuestionAdapter(
    private var items: List<Question>,
    private val listener: ListenerList?
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    interface ListenerList {
        fun onItemClick(id: Int, adapterPosition: Int)
    }

    inner class ViewHolder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener!!.onItemClick(
                    items[adapterPosition].id,
                    adapterPosition
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemQuestionBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_question, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textData.text = items[position].question
        holder.binding.executePendingBindings()

    }
}
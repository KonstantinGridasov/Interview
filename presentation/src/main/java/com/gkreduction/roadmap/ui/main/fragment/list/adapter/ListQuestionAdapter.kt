package com.gkreduction.roadmap.ui.main.fragment.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.ItemListBinding

class ListQuestionAdapter(
    var onTitleItemListener: OnTitleItemListener?
) : RecyclerView.Adapter<ListQuestionAdapter.ViewHolder>() {
    private var items: List<QuestionAnswer> = emptyList()

    inner class ViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemListBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_list, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textQuestion.text = items[position].question

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<QuestionAnswer>) {
        this.items = items
        notifyDataSetChanged()
    }


}
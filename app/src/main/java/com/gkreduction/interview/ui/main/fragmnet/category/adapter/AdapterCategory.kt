package com.gkreduction.interview.ui.main.fragmnet.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.ItemCategoryBinding
import com.gkreduction.interview.entity.Category

class AdapterCategory(
    private var items: List<Category>,
    private val listener: ListenerList?
) : RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    interface ListenerList {
        fun onCategoryClick(type: String)
    }

    inner class ViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { listener!!.onCategoryClick(items[adapterPosition].category) }
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
        holder.binding.textData.text = items[position].category
    }

}
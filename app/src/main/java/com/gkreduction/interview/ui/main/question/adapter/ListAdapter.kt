package com.gkreduction.interview.ui.main.question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.ItemListBinding
import com.gkreduction.interview.entity.DataInfo
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(
    private var items: List<DataInfo>,
    private val listener: ListenerList?,
    private val typeList: String
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    interface ListenerList {
        fun onItemClick(item: DataInfo, type: String)
    }

    inner class ViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { listener!!.onItemClick(items[adapterPosition], typeList) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemListBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_list, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (typeList == "type")
            holder.itemView.textData.text = items[position].category
        else
            holder.itemView.textData.text = items[position].question

        holder.binding.executePendingBindings()

    }
}
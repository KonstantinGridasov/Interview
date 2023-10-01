package com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter

import com.gkreduction.domain.entity.BaseItem

interface OnSubtopicListener {
    fun onSubtopicClick(item: BaseItem)
    fun onSubtopicLongClick(item: BaseItem)

}
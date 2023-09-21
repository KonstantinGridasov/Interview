package com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter

import com.gkreduction.domain.entity.BaseItem

interface OnTopicListener {
    fun onTopicClick(item: BaseItem)
}
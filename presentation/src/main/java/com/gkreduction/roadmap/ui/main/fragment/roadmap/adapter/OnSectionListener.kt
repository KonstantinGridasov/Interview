package com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter

import com.gkreduction.domain.entity.BaseItem

interface OnSectionListener {
    fun onSectionClick(item: BaseItem)
    fun onSectionLongClick(item: BaseItem)
}
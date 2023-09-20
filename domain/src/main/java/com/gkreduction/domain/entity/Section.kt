package com.gkreduction.domain.entity

class Section(
    override var id: Long,
    override var name: String,
    var position: Int,
    var topics: List<BaseElement>
) :
    BaseElement(id, name)
package com.gkreduction.domain.entity

class Section(
    override var id: Int,
    override var name: String,
    var position: Int,
    var topics: List<Topic>
) :
    BaseElement(id, name)
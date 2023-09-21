package com.gkreduction.domain.entity

class Section(
    var id: Long,
    var name: String,
    var position: Int,
    var topics: List<Topic>
)
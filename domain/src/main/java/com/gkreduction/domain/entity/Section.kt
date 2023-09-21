package com.gkreduction.domain.entity

import java.io.Serializable

class Section(
    override var id: Long,
    override var name: String,
    var position: Int,
    var topics: List<Topic>
) : BaseItem(id, name), Serializable
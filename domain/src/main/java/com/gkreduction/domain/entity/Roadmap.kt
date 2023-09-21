package com.gkreduction.domain.entity

import java.io.Serializable

class Roadmap(override var id: Long, override var name: String, var section: List<Section>) :
    BaseItem(id, name), Serializable
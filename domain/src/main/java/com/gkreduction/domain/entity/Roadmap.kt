package com.gkreduction.domain.entity

class Roadmap(override var id: Int, override var name: String, var section: List<Section>) :
    BaseElement(id, name)
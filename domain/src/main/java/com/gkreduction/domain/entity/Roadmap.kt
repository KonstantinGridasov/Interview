package com.gkreduction.domain.entity

class Roadmap(override var id: Long, override var name: String, var section: List<BaseElement>) :
    BaseElement(id, name)
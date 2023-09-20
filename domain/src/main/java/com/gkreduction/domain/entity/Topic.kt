package com.gkreduction.domain.entity

class Topic(override var id: Long, override var name: String, var subtopics: List<BaseElement>) :
    BaseElement(id, name)
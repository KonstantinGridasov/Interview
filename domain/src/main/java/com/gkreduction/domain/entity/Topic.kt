package com.gkreduction.domain.entity

class Topic(override var id: Int, override var name: String, var subtopics: List<Subtopic>) :
    BaseElement(id, name)
package com.gkreduction.domain.entity

import java.io.Serializable

class Topic(override var id: Long, override var name: String, var subtopics: List<Subtopic>) :
    BaseItem(id, name),
    Serializable
package com.gkreduction.domain.entity

class Topic(var id: Long, var name: String, var subtopics: List<Subtopic>) {
    override fun toString(): String {
        return name
    }
}
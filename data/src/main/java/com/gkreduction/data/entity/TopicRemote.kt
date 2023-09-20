package com.gkreduction.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TopicRemote(
    @SerialName("id") val id: Long?,
    @SerialName("name") val name: String?,
    @SerialName("subtopics") val subtopics: List<SubtopicRemote>,
):BaseRemote

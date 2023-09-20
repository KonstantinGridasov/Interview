package com.gkreduction.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SectionRemote(
    @SerialName("id") val id: Long?,
    @SerialName("name") val name: String?,
    @SerialName("position") val position: Int?,
    @SerialName("topics") val topics: List<TopicRemote>,
):BaseRemote

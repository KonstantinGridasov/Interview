package com.gkreduction.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RoadmapRemote(
    @SerialName("id") val id: Int?,
    @SerialName("name") val name: String?,
    @SerialName("sections") val sections: List<SectionRemote>?,
) : BaseRemote

package com.gkreduction.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QASectionRemote(
    @SerialName("id") val id: Int?,
    @SerialName("name") val name: String?,
)
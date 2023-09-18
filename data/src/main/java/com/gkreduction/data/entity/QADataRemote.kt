package com.gkreduction.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QADataRemote(
    @SerialName("id") var id: Int,
    @SerialName("name") var name: String,
)
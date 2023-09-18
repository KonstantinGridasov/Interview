package com.gkreduction.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QuestionAnswerRemote(
    @SerialName("position") val position: Int?,
    @SerialName("id") val id: Long?,
    @SerialName("question") val question: String?,
    @SerialName("answer") val answer: String?,

    @SerialName("roadmap") var roadmap: List<QADataRemote>,
    @SerialName("section") var section: List<QADataRemote>,
    @SerialName("topic") var topic: List<QADataRemote>,
    @SerialName("subtopic") var subtopic: List<QADataRemote>,
)

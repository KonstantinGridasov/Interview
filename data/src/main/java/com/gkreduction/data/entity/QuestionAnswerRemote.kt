package com.gkreduction.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionAnswerRemote(
    @SerialName("question") val question: String?,
    @SerialName("answer") val answer: String?,
    @SerialName("id") val id: Int?,
    @SerialName("roadmap") val roadmap: QARoadmapRemote?,
    @SerialName("section") val section: QASectionRemote?,
    @SerialName("topic") val topic: QATopicRemote?,
    @SerialName("subtopic") val subtopic: QASubTopicRemote?,
    )

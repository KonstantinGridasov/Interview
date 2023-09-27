package com.gkreduction.data.repository.network

import com.gkreduction.data.ApiConstants.Companion.GET_QUESTION
import com.gkreduction.data.ApiConstants.Companion.GET_ROADMAPS
import com.gkreduction.data.entity.QuestionAnswerRemote
import com.gkreduction.data.entity.RoadmapRemote
import retrofit2.http.GET

interface NetworkApi {
    @GET(GET_ROADMAPS)
    suspend fun getRoadmaps(): List<RoadmapRemote>

    @GET(GET_QUESTION)
    suspend fun getQA(): List<QuestionAnswerRemote>
}
package com.gkreduction.data.repository.network

import com.gkreduction.data.entity.QuestionAnswerRemote
import com.gkreduction.data.entity.RoadmapRemote
import retrofit2.http.GET

interface NetworkApi {
    @GET("api/files/api/interview.json")
    suspend fun getRoadmaps(): List<RoadmapRemote>

    @GET("api/files/api/qa.json")
    suspend fun getQA(): List<QuestionAnswerRemote>
}
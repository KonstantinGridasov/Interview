package com.gkreduction.data.repository

import com.gkreduction.data.entity.RoadmapRemote
import retrofit2.http.GET

interface NetworkApi {
    @GET("api/files/api/interview.json")
    suspend fun getRoadmaps(): List<RoadmapRemote>
}
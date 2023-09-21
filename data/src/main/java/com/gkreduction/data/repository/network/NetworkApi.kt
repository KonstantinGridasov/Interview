package com.gkreduction.data.repository.network

import com.gkreduction.data.entity.QuestionAnswerRemote
import com.gkreduction.data.entity.RoadmapRemote
import io.reactivex.Observable
import retrofit2.http.GET

interface NetworkApi {
    @GET("api/files/api/interview.json")
    fun getRoadmaps(): Observable<List<RoadmapRemote>>

    @GET("api/files/api/qa.json")
    fun getQA(): Observable<List<QuestionAnswerRemote>>
}
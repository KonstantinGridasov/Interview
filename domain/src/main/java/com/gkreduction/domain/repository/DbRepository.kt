package com.gkreduction.domain.repository

import com.gkreduction.domain.entity.Roadmap
import io.reactivex.Observable

interface DbRepository {
    fun updateQuestion(): Observable<Boolean>

    fun updateDb(): Observable<Boolean>
    fun getRoadmaps(): Observable<List<Roadmap>>

}
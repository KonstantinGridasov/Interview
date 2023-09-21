package com.gkreduction.domain.repository

import com.gkreduction.domain.entity.BaseElement
import com.gkreduction.domain.entity.Roadmap
import io.reactivex.Observable

interface NetworkRepository {
     fun getRoadmaps(): Observable<List<BaseElement>>
}
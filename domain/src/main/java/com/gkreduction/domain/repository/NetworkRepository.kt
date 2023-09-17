package com.gkreduction.domain.repository

import com.gkreduction.domain.entity.BaseElement
import com.gkreduction.domain.entity.Roadmap

interface NetworkRepository {
    suspend fun getRoadmaps(): List<BaseElement>
}
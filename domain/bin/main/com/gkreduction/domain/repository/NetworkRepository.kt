package com.gkreduction.domain.repository

interface NetworkRepository {
    suspend fun getRoadmaps(): List<Any>
}
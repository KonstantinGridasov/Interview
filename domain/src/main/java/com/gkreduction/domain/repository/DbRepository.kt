package com.gkreduction.domain.repository

interface DbRepository {
    suspend fun updateQuestion(): Boolean

    suspend fun updateDb(): Boolean

}
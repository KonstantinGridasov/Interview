package com.gkreduction.domain.repository

interface DbRepository {
    suspend fun updateDb(): Boolean
}
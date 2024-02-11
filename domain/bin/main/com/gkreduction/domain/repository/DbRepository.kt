package com.gkreduction.domain.repository

import com.gkreduction.domain.entity.BaseItem
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.entity.Roadmap

interface DbRepository {
    suspend fun updateQuestion(): Boolean
    suspend fun updateDb(): Boolean
    suspend fun getRoadmaps(): List<Roadmap>
    suspend fun getRoadmapById(id: Long): Roadmap
    suspend fun getQuestionByItem(
        item: BaseItem?,
        random: Boolean,
        size: Int
    ): List<QuestionAnswer>

    suspend fun getQuestionById(param: Long): QuestionAnswer

}
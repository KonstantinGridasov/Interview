package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.repository.DbRepository

class GetRandomQuestion(var repository: DbRepository) :
    UseCase<Int, List<QuestionAnswer>> {
    override suspend fun execute(param: Int?): List<QuestionAnswer> {
        return repository.getRandomQuestion(param ?: 20)
    }
}
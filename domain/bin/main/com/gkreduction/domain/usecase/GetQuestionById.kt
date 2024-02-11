package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.repository.DbRepository

class GetQuestionById(var repository: DbRepository) :
    UseCase<Long, QuestionAnswer> {
    override suspend fun execute(param: Long?): QuestionAnswer {
        return repository.getQuestionById(param!!)
    }
}
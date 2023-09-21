package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.BaseItem
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.repository.DbRepository

class GetListQuestionByItem(var repository: DbRepository) :
    UseCase<BaseItem, List<QuestionAnswer>> {
    override suspend fun execute(item: BaseItem?): List<QuestionAnswer> {
        return repository.getQuestionByItem(item)
    }
}
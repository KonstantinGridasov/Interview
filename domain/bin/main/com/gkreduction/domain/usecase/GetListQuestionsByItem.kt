package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.BaseItem
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.repository.DbRepository

class GetListQuestionsByItem(var repository: DbRepository) :
    UseCase<GetListQuestionsByItem.Params, List<QuestionAnswer>> {
    override suspend fun execute(param: Params?): List<QuestionAnswer> {
        return repository.getQuestionByItem(param!!.item, param.random, param.size)
    }

    data class Params(var item: BaseItem, var random: Boolean = false, var size: Int = -1)
}
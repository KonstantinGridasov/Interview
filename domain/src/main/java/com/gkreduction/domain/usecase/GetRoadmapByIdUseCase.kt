package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.ItemRoadmap
import com.gkreduction.domain.repository.DbRepository

class GetRoadmapByIdUseCase(var repository: DbRepository) : UseCase<Long, List<ItemRoadmap>> {
    override suspend fun execute(param: Long?): List<ItemRoadmap> {
        return repository.getRoadmapById(param!!)
    }
}
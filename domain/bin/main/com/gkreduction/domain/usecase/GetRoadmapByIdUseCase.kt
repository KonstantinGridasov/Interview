package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.repository.DbRepository

class GetRoadmapByIdUseCase(var repository: DbRepository) : UseCase<Long, Roadmap> {
    override suspend fun execute(param: Long?): Roadmap {
        return repository.getRoadmapById(param!!)
    }
}
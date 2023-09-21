package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.repository.DbRepository

class GetRoadmapsUseCase(var repository: DbRepository) : UseCase<Void, List<Roadmap>> {
    override suspend fun execute(param: Void?): List<Roadmap> {
        return repository.getRoadmaps()
    }
}
package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.BaseElement
import com.gkreduction.domain.repository.NetworkRepository

class GetRoadmapsUseCase(var repository: NetworkRepository) : UseCase<Void, List<BaseElement>> {
    override suspend fun execute(request: Void?): List<BaseElement> {
        return repository.getRoadmaps()
    }
}
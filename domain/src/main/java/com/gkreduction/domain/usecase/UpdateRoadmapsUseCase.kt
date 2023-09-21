package com.gkreduction.domain.usecase

import com.gkreduction.domain.repository.DbRepository

class UpdateRoadmapsUseCase(var repository: DbRepository) : UseCase<Void, Boolean> {
    override suspend fun execute(param: Void?): Boolean {
        return repository.updateDb()
    }
}
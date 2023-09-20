package com.gkreduction.domain.usecase

import com.gkreduction.domain.repository.DbRepository

class UpdateQaUseCase(var repository: DbRepository) : UseCase<Void, Boolean> {
    override suspend fun execute(request: Void?): Boolean {
        return repository.updateQuestion()
    }
}
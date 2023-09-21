package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.repository.DbRepository
import io.reactivex.Observable

class UpdateQaUseCase(var repository: DbRepository) : UseCase<Void,  Observable<Boolean>> {
    override  fun execute(request: Void?): Observable<Boolean> {
        return repository.updateQuestion()
    }
}
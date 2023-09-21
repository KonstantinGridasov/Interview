package com.gkreduction.domain.usecase

import com.gkreduction.domain.repository.DbRepository
import io.reactivex.Observable

class UpdateRoadmapsUseCase(var repository: DbRepository) : UseCase<Void, Observable<Boolean>> {
    override fun execute(request: Void?): Observable<Boolean> {
        return repository.updateDb()
    }
}
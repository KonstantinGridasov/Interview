package com.gkreduction.domain.usecase

import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.repository.DbRepository
import io.reactivex.Observable

class GetRoadmapsUseCase(var repository: DbRepository) : UseCase<Void, Observable<List<Roadmap>>> {
    override fun execute(request: Void?): Observable<List<Roadmap>> {
        return repository.getRoadmaps()
    }
}
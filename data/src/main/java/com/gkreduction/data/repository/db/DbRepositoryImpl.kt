package com.gkreduction.data.repository.db

import com.gkreduction.data.mapper.mapperRoadmapsDbToCore
import com.gkreduction.data.repository.db.dao.QuestionAnswerDao
import com.gkreduction.data.repository.db.dao.RoadmapDao
import com.gkreduction.data.repository.network.datasource.NetworkDataStore
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.repository.DbRepository
import io.reactivex.Observable

class DbRepositoryImpl(
    var roadmapDao: RoadmapDao,
    var qaDao: QuestionAnswerDao,
    var network: NetworkDataStore
) : DbRepository {


    override fun updateQuestion(): Observable<Boolean> {
        return network.getQa()
            .flatMap { it ->
                qaDao.insert(it)
                return@flatMap Observable.just(true)

            }
    }

    override fun updateDb(): Observable<Boolean> {
        return network.getNetworkRoadmaps()
            .flatMap { it ->
                roadmapDao.insert(it)
                return@flatMap Observable.just(true)

            }


    }

    override fun getRoadmaps(): Observable<List<Roadmap>> {
        return Observable.just(true)
            .flatMap {
                return@flatMap Observable.just(roadmapDao.getAllRoadmaps())
                    .map { mapperRoadmapsDbToCore(it) }
            }
    }
}
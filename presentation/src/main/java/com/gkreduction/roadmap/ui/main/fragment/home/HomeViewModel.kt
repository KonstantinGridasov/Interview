package com.gkreduction.roadmap.ui.main.fragment.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableArrayList
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.usecase.GetRoadmapsUseCase
import com.gkreduction.domain.usecase.UpdateQaUseCase
import com.gkreduction.domain.usecase.UpdateRoadmapsUseCase
import com.gkreduction.roadmap.ui.base.BaseAndroidViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    context: Context,
    var updateRoadmapsUseCase: UpdateRoadmapsUseCase,
    var updateQaUseCase: UpdateQaUseCase,
    var getRoadmapsUseCase: GetRoadmapsUseCase
) :
    BaseAndroidViewModel(context.applicationContext as Application) {

    private var updateRoadmapsDis: Disposable? = null
    private var updateQaDis: Disposable? = null
    private var getRoadmapDis: Disposable? = null

    //    var roadmaps: ObservableField<Roadmap>
    var roadmaps: ObservableArrayList<Roadmap> = ObservableArrayList()


    fun getRoadmapsFromDb() {
        if (getRoadmapDis != null)
            removeDisposable(getRoadmapDis!!)

        getRoadmapDis = getRoadmapsUseCase
            .execute()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Roadmap>>() {
                override fun onComplete() {
                }

                override fun onNext(t: List<Roadmap>) {
                    updateRoadmaps(t)
                }

                override fun onError(e: Throwable) {
                }

            })
//            .subscribe {
//                Log.d("BindingRoadmapAdapter", " items size = ${it.size}")
//
//                roadmaps.set(it)
//                roadmaps.notifyChange()
//            }

        addDisposable(getRoadmapDis!!)


    }

    private fun updateRoadmaps(t: List<Roadmap>) {
        Log.d("BindingRoadmapAdapter", " updateRoadmaps = ${t.size}")

        this.roadmaps.addAll(t)
//        roadmaps.notifyChange()
        Log.d("BindingRoadmapAdapter", " updateRoadmaps roadmaps = ${roadmaps.size}")

    }

    fun fetchRoadmaps() {
        if (updateRoadmapsDis != null)
            removeDisposable(updateRoadmapsDis!!)

        updateRoadmapsDis = updateRoadmapsUseCase
            .execute()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it)
                    getRoadmapsFromDb()
            }

        addDisposable(updateRoadmapsDis!!)

    }

    fun fetchQA() {

        if (updateQaDis != null)
            removeDisposable(updateQaDis!!)

        updateQaDis = updateQaUseCase
            .execute()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {}

        addDisposable(updateQaDis!!)

    }
}
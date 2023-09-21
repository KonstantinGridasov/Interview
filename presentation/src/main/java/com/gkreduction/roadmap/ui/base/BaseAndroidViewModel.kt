package com.gkreduction.roadmap.ui.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@Suppress("ConvertSecondaryConstructorToPrimary")
@SuppressLint("StaticFieldLeak")
abstract class BaseAndroidViewModel : AndroidViewModel {

    protected val context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }


    private val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.dispose()
    }


    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun removeDisposable(disposable: Disposable) {
        disposables.remove(disposable)
    }

}

package com.gkreduction.interview.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

@Suppress("ConvertSecondaryConstructorToPrimary")
@SuppressLint("StaticFieldLeak")
abstract class BaseAndroidViewModel : AndroidViewModel {

    protected val context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }


}

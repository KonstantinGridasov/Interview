package com.gkreduction.roadmap.ui.base


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import com.gkreduction.roadmap.utils.lazyThreadSafetyNone
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

open class BaseDialogFragment<T : BaseAndroidViewModel>(
    var viewId: Int,
    var modelClass: Class<T>
) :
    DaggerDialogFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: ViewDataBinding

    protected val viewModel by lazyThreadSafetyNone {
        activity?.let {
            ViewModelProvider(it, viewModelFactory).get(modelClass)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mDialog = activity?.let { Dialog(it) }
        mDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            viewId, null, false
        )
        mDialog?.setContentView(binding.root)
        mDialog.let { it ->
            it?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            it?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }
        initialized()
        return mDialog!!
    }

    open fun initialized() {
    }

}
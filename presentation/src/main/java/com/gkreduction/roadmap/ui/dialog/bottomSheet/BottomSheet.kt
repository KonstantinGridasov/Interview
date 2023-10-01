package com.gkreduction.roadmap.ui.dialog.bottomSheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.gkreduction.domain.entity.BaseItem
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.DialogBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: DialogBottomBinding

    private var baseItem: BaseItem? = null
    private var onChooseList = {}
    private var onChooseTheory = {}
    private var onChooseExam = {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mDialog = activity?.let { BottomSheetDialog(it) }
        mDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_bottom, null, false
        )
        mDialog?.setContentView(binding.root)
        mDialog.let { it ->
            it?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        initialized()
        return mDialog!!
    }


    private fun initialized() {
        binding.title.text = baseItem?.name
        binding.cvExam.setOnClickListener {
            onChooseExam.invoke()
            this.dismiss()
        }
        binding.cvList.setOnClickListener {
            onChooseList.invoke()
            this.dismiss()
        }
        binding.cvTheory.setOnClickListener {
            onChooseTheory.invoke()
            this.dismiss()
        }
    }

    fun setParams(
        item: BaseItem,
        onChooseList: () -> Unit,
        onChooseTheory: () -> Unit,
        onChooseExam: () -> Unit
    ): BottomSheet {
        this.baseItem = item
        this.onChooseTheory = onChooseTheory
        this.onChooseList = onChooseList
        this.onChooseExam = onChooseExam
        return this
    }
}
package com.gkreduction.roadmap.ui.dialog.finish

import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.DialogFinishBinding
import com.gkreduction.roadmap.ui.base.BaseDialogFragment

class FinishDialog : BaseDialogFragment<FinishViewModel>(
    R.layout.dialog_finish,
    FinishViewModel::class.java
) {
    private var isSuccess: Boolean = false
    private var listener: (() -> Unit) = {}

    override fun initialized() {
        super.initialized()
        if (isSuccess)
            (binding as DialogFinishBinding).animation.setAnimation("animation_success.json")
        else
            (binding as DialogFinishBinding).animation.setAnimation("bad.json")

        (binding as DialogFinishBinding).ivClose.setOnClickListener {
            listener.invoke()
            this.dismiss()
        }
    }

    fun setParams(isSuccess: Boolean, listener: () -> Unit): FinishDialog {
        this.isSuccess = isSuccess
        this.listener = listener
        return this
    }

}
package com.gkreduction.roadmap.ui.dialog.finish

import android.view.View
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.DialogFinishBinding
import com.gkreduction.roadmap.ui.base.BaseDialogFragment

class FinishDialog : BaseDialogFragment<FinishViewModel>(
    R.layout.dialog_finish,
    FinishViewModel::class.java
), View.OnClickListener {
    private var isSuccess: Boolean = false
    private var listenerFinish: (() -> Unit) = {}
    private var listenerRestart: (() -> Unit) = {}

    override fun initialized() {
        super.initialized()
        (binding as DialogFinishBinding).listener = this
        if (isSuccess)
            (binding as DialogFinishBinding).animation.setAnimation("animation_success.json")
        else
            (binding as DialogFinishBinding).animation.setAnimation("bad.json")
    }

    fun setParams(
        isSuccess: Boolean,
        listenerFinish: () -> Unit,
        listenerRestart: () -> Unit
    ): FinishDialog {
        this.isSuccess = isSuccess
        this.listenerFinish = listenerFinish
        this.listenerRestart = listenerRestart
        return this
    }

    override fun onClick(p0: View?) {
        when (p0) {
            (binding as DialogFinishBinding).bFinish -> {
                listenerFinish.invoke()
                this.dismiss()
            }
            (binding as DialogFinishBinding).bRestart -> {
                listenerRestart.invoke()
                this.dismiss()
            }
        }

    }

}
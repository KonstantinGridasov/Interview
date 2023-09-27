package com.gkreduction.roadmap.ui.dialog.help

import android.text.Html
import android.text.Spanned
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.DialogHelpBinding
import com.gkreduction.roadmap.ui.base.BaseDialogFragment

class HelpDialog : BaseDialogFragment<HelpDialogViewModel>(
    R.layout.dialog_help,
    HelpDialogViewModel::class.java
) {
    private var answer: String = ""
    private var listener: (() -> Unit) = {}


    override fun initialized() {
        super.initialized()
        (binding as DialogHelpBinding).ivClos.setOnClickListener {
            listener.invoke()
            this.dismiss()
        }
        (binding as DialogHelpBinding).textAnswer.text = getText(answer)
    }


    fun setParams(answer: String, listener: () -> Unit): HelpDialog {
        this.listener = listener
        this.answer = answer
        return this
    }

    private fun getText(string: String): Spanned {
        return Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY)
    }
}
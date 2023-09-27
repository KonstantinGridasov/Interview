package com.gkreduction.roadmap.ui.dialog

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

    fun setParams(answer: String): HelpDialog {
        this.answer = answer
        return this
    }

    private fun getText(string: String): Spanned {
        return Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY)
    }

    override fun initialized() {
        super.initialized()
        (binding as DialogHelpBinding).ivClos.setOnClickListener {
            this.dismiss()
        }

        (binding as DialogHelpBinding).textAnswer.text = getText(answer)

    }
}
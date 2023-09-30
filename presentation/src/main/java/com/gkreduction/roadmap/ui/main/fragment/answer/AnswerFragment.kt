package com.gkreduction.roadmap.ui.main.fragment.answer

import android.text.Html
import android.text.Spanned
import android.view.View
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentAnswerBinding
import com.gkreduction.roadmap.ui.base.BaseFragment


class AnswerFragment : BaseFragment<AnswerViewModel>(
    R.layout.fragment_answer,
    AnswerViewModel::class.java
), View.OnClickListener {

    override fun onStart() {
        super.onStart()
        val item = AnswerFragmentArgs.fromBundle(requireArguments()).item
        viewModel?.getAnswersByItem(item)
        initListeners()
    }

    private fun initListeners() {
        (binding as FragmentAnswerBinding).listener = this
        activity?.let {
            viewModel?.answer?.observe(it) { item ->
                (binding as FragmentAnswerBinding).textQuestion.text = item.question
                (binding as FragmentAnswerBinding).textAnswer.text = getText(item.answer)
            }
        }

    }

    private fun getText(string: String): Spanned {
        return Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            (binding as FragmentAnswerBinding).ivNext -> viewModel?.onNextAnswer()
            (binding as FragmentAnswerBinding).ivPreview -> viewModel?.onPreviewAnswer()
        }
    }

}
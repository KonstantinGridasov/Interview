package com.gkreduction.roadmap.ui.main.fragment.answer

import android.text.Html
import android.text.Spanned
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentAnswerBinding
import com.gkreduction.roadmap.ui.base.BaseFragment


class AnswerFragment : BaseFragment<AnswerViewModel>(
    R.layout.fragment_answer,
    AnswerViewModel::class.java
) {

    override fun onStart() {
        super.onStart()
        val args = AnswerFragmentArgs.fromBundle(requireArguments())
        viewModel?.getAnswerByQuestionId(args.id)
        initObservers()
    }

    private fun initObservers() {
        activity?.let {
            viewModel?.question?.observe(it) { item ->
                (binding as FragmentAnswerBinding).textQuestion.text = item.question
                (binding as FragmentAnswerBinding).textAnswer.text = getText(item.answer)
            }
        }

    }

    private fun getText(string: String): Spanned {
        return Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY)
    }

}
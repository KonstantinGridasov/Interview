package com.gkreduction.roadmap.ui.main.fragment.theory

import android.text.Html
import android.text.Spanned
import android.view.View
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentTheoryBinding
import com.gkreduction.roadmap.ui.base.BaseFragment


class TheoryFragment : BaseFragment<TheoryViewModel>(
    R.layout.fragment_theory,
    TheoryViewModel::class.java
), View.OnClickListener {

    override fun onStart() {
        super.onStart()
        val item = TheoryFragmentArgs.fromBundle(requireArguments()).item
        val id = TheoryFragmentArgs.fromBundle(requireArguments()).id
        viewModel?.getTheoryByItem(item, id)
        initListeners()
    }

    private fun initListeners() {
        (binding as FragmentTheoryBinding).listener = this
        activity?.let {
            viewModel?.answer?.observe(it) { item ->
                (binding as FragmentTheoryBinding).textQuestion.text = item.question
                (binding as FragmentTheoryBinding).textAnswer.text = getText(item.answer)
            }
        }

    }

    private fun getText(string: String): Spanned {
        return Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            (binding as FragmentTheoryBinding).ivNext -> viewModel?.onNextAnswer()
            (binding as FragmentTheoryBinding).ivPreview -> viewModel?.onPreviewAnswer()
        }
    }

}
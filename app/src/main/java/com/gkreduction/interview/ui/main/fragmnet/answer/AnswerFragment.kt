package com.gkreduction.interview.ui.main.fragmnet.answer

import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.FragmentAnswerBinding
import com.gkreduction.interview.ui.base.BaseFragment
import com.gkreduction.interview.ui.main.MainActivity


class AnswerFragment : BaseFragment<AnswerViewModel>(
    R.layout.fragment_answer,
    AnswerViewModel::class.java
) {

    override fun onStart() {
        super.onStart()
        (binding as FragmentAnswerBinding).viewModel = viewModel

        val args = AnswerFragmentArgs.fromBundle(requireArguments())
        getAnswer(args.id)
    }

    private fun getAnswer(id: Int) {
        activity?.let {
            if (it is MainActivity)
                viewModel?.getAnswer(id, it.data!!)
        }
        activity?.let {
            if (it is MainActivity) {
                val dataInfo = it.data?.find { item -> (item.id == id) }
                it.setToolbarName(name = dataInfo?.category ?: "")

            }
        }
    }

}
package com.gkreduction.interview.ui.main.fragmnet.question

import androidx.navigation.findNavController
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.FragmentQuestionBinding
import com.gkreduction.interview.ui.base.BaseFragment
import com.gkreduction.interview.ui.main.MainActivity
import com.gkreduction.interview.ui.main.fragmnet.question.adapter.QuestionAdapter

class QuestionFragment : BaseFragment<QuestionViewModel>(
    R.layout.fragment_question,
    QuestionViewModel::class.java
), QuestionAdapter.ListenerList {

    override fun onStart() {
        super.onStart()
        (binding as FragmentQuestionBinding).viewModel = viewModel
        (binding as FragmentQuestionBinding).listener = this


        val args = QuestionFragmentArgs.fromBundle(requireArguments())
        if (args.cat.isNotEmpty())
            initQuestion(args.cat)
    }

    private fun initQuestion(cat: String) {
        activity?.let {
            if (it is MainActivity)
                viewModel?.initQuestion(cat, it.data!!)
        }
        activity?.let {
            if (it is MainActivity)
                it.setToolbarName(cat)
        }
    }


    override fun onItemClick(id: Int) {
        view?.findNavController()?.navigate(QuestionFragmentDirections.actionQuestionToAnswer(id))
    }
}


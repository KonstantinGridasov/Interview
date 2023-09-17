package com.gkreduction.interview.ui.main.fragment.question

import androidx.navigation.findNavController
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.FragmentQuestionBinding
import com.gkreduction.interview.ui.base.BaseFragment
import com.gkreduction.interview.ui.main.MainActivity
import com.gkreduction.interview.ui.main.fragment.question.adapter.QuestionAdapter

class QuestionFragment : BaseFragment<QuestionViewModel>(
    R.layout.fragment_question,
    QuestionViewModel::class.java
), QuestionAdapter.ListenerList {

    override fun onStart() {
        super.onStart()
        (binding as FragmentQuestionBinding).viewModel = viewModel
        (binding as FragmentQuestionBinding).listener = this


        val args =
            com.gkreduction.interview.ui.main.fragment.question.QuestionFragmentArgs.fromBundle(
                requireArguments()
            )
        if (args.cat.isNotEmpty())
            initQuestion(args.cat)
        initializeAdaptersRecycler()
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


    private fun initializeAdaptersRecycler() {
        val position = viewModel?.position ?: 0
        (binding as FragmentQuestionBinding).rvQuestion.scrollToPosition(position)
    }

    override fun onItemClick(id: Int, adapterPosition: Int) {
        viewModel?.position = adapterPosition
        view?.findNavController()?.navigate(
            com.gkreduction.interview.ui.main.fragment.question.QuestionFragmentDirections.actionQuestionToAnswer(
                id
            )
        )
    }
}


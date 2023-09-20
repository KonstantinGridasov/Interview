package com.gkreduction.roadmap.ui.main.fragment.question

import androidx.navigation.findNavController
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentQuestionBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
import com.gkreduction.roadmap.ui.main.MainActivity
import com.gkreduction.roadmap.ui.main.fragment.question.adapter.QuestionAdapter

class QuestionFragment : BaseFragment<QuestionViewModel>(
    R.layout.fragment_question,
    QuestionViewModel::class.java
), QuestionAdapter.ListenerList {

    override fun onStart() {
        super.onStart()
        (binding as FragmentQuestionBinding).viewModel = viewModel
        (binding as FragmentQuestionBinding).listener = this


        val args = QuestionFragmentArgs.fromBundle(
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
            QuestionFragmentDirections.actionQuestionToAnswer(
                id
            )
        )
    }
}


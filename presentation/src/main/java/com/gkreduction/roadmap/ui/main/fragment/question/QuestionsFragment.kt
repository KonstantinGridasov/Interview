package com.gkreduction.roadmap.ui.main.fragment.question

import androidx.navigation.findNavController
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentQuestionsBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
import com.gkreduction.roadmap.ui.main.MainActivity
import com.gkreduction.roadmap.ui.main.fragment.question.adapter.OnTitleItemListener
import com.gkreduction.roadmap.ui.main.fragment.question.adapter.QuestionAdapter


class QuestionsFragment : BaseFragment<QuestionsViewModel>(
    R.layout.fragment_questions,
    QuestionsViewModel::class.java
), OnTitleItemListener {
    lateinit var adapter: QuestionAdapter
    override fun onStart() {
        super.onStart()

        val item = QuestionsFragmentArgs.fromBundle(requireArguments()).item
        viewModel?.getQuestionsById(item)
        setToolbarName(item.name)
        initAdapters()
        initObservers()
    }

    private fun setToolbarName(name: String) {
        activity?.let {
            (it as MainActivity).setToolbarName(name)
        }

    }


    private fun initAdapters() {
        adapter = QuestionAdapter(this)
        (binding as FragmentQuestionsBinding).rvListItem.adapter = adapter
    }


    private fun initObservers() {
        activity?.let {
            viewModel?.roadmap?.observe(it) { item ->
                adapter.updateItems(item)
            }
        }

    }

    override fun onTitleClick(id: Long) {
        view?.findNavController()
            ?.navigate(QuestionsFragmentDirections.questionToAnswer(id))
    }


}
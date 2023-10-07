package com.gkreduction.roadmap.ui.main.fragment.exam

import android.view.View
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentExamBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
import com.gkreduction.roadmap.ui.main.MainActivity

class ExamFragment :
    BaseFragment<ExamViewModel>(
        R.layout.fragment_exam,
        ExamViewModel::class.java
    ), View.OnClickListener {

    private var inProcess = false

    override fun initialize() {
        super.initialize()
        (binding as FragmentExamBinding).viewModel = viewModel
        (binding as FragmentExamBinding).listener = this
        val item = ExamFragmentArgs.fromBundle(requireArguments()).item
        viewModel?.updateItem(item)
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        inProcess = true

    }

    override fun onStop() {
        super.onStop()
        inProcess = false
    }

    private fun initObservers() {
        activity?.let {
            viewModel?.onSkip?.observe(it) { item ->
                (binding as FragmentExamBinding).skip.text = viewModel?.getTitleByItem(item)
            }
            viewModel?.onBad?.observe(it) { item ->
                (binding as FragmentExamBinding).bad.text = viewModel?.getTitleByItem(item)
            }
            viewModel?.onRight?.observe(it) { item ->
                (binding as FragmentExamBinding).right.text = viewModel?.getTitleByItem(item)
            }

            viewModel?.question?.observe(it) { item ->
                (binding as FragmentExamBinding).textQuestion.text = item
            }

            viewModel?.answer?.observe(it) { item ->
                if (item.isNotEmpty())
                    showDialog(item)
            }
            viewModel?.statusFinish?.observe(it) { item ->
                if (inProcess)
                    showDialogFinish(item)
            }
        }

    }

    override fun onClick(p0: View?) {
        when (p0) {
            (binding as FragmentExamBinding).buttonSkip -> {
                viewModel?.onSkipClick()
            }
            (binding as FragmentExamBinding).ivBad -> {
                viewModel?.onBadClick()
            }
            (binding as FragmentExamBinding).ivRight -> {
                viewModel?.onRightClick()
            }
            (binding as FragmentExamBinding).ivHelp -> {
                viewModel?.getAnswerId()
            }
        }
    }

    private fun showDialog(item: String) {
        (activity as? MainActivity)?.showDialogHelp(item) { viewModel?.clearAnswer() }

    }

    private fun showDialogFinish(status: Boolean) {
        (activity as? MainActivity)?.showDialogFinish(
            status,
            listenerFinish = { closeExam() },
            listenerRestart = { viewModel?.clearQuestion() }
        )
    }

    private fun closeExam() {
        (activity as? MainActivity)?.onBack()
    }
}


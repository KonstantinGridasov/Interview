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

    override fun onStart() {
        super.onStart()
        (binding as FragmentExamBinding).viewModel = viewModel
        (binding as FragmentExamBinding).listener = this
        activity?.let {
            if (it is MainActivity) {
                viewModel?.update(it.data)
            }
        }

        activity?.let {
            if (it is MainActivity)
                it.setToolbarName(it.resources.getString(R.string.app_name))
        }
        updateQuestion()
    }


    override fun onClick(p0: View?) {
        when (p0) {
            (binding as FragmentExamBinding).textQuestion -> (binding as FragmentExamBinding).textAnswer.visibility =
                View.VISIBLE
            (binding as FragmentExamBinding).next -> {
                updateQuestion()
            }

        }
    }

    private fun updateQuestion() {
        if ((binding as FragmentExamBinding).textAnswer.visibility == View.GONE)
            (binding as FragmentExamBinding).textAnswer.visibility = View.VISIBLE
        else {
            (binding as FragmentExamBinding).textAnswer.visibility = View.GONE
            viewModel?.updateRandom()
        }
    }
}


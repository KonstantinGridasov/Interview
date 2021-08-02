package com.gkreduction.interview.ui.main.answer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.FragmentAnswerBinding
import com.gkreduction.interview.ui.main.MainActivity
import com.gkreduction.interview.utils.lazyThreadSafetyNone
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnswerFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binder: FragmentAnswerBinding

    private val viewModel by lazyThreadSafetyNone {
        activity?.let {
            ViewModelProvider(
                it,
                viewModelFactory
            ).get(AnswerViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_answer, container, false)
        binder.viewModel = viewModel
        val args = AnswerFragmentArgs.fromBundle(requireArguments())
        getAnswer(args.id)
        return binder.root
    }

    private fun getAnswer(id: Int) {
        activity?.let {
            if (it is MainActivity)
                viewModel!!.getAnswer(id, it.data!!)
        }
    }

}
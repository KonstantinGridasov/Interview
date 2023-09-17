package com.gkreduction.interview.ui.main.fragment.home

import android.view.View
import androidx.navigation.findNavController
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.FragmentHomeBinding
import com.gkreduction.interview.ui.base.BaseFragment
import com.gkreduction.interview.ui.main.MainActivity

class HomeFragment :
    BaseFragment<HomeViewModel>(
        R.layout.fragment_home,
        HomeViewModel::class.java
    ), View.OnClickListener {
    override fun onStart() {
        super.onStart()
        (binding as FragmentHomeBinding).listener = this
        activity?.let {
            if (it is MainActivity)
                it.setToolbarName(it.resources.getString(R.string.app_name))
        }
    }


    override fun onClick(p0: View?) {
        when (p0) {
            (binding as FragmentHomeBinding).exam -> viewModel?.fetchRoadmaps()
//            (binding as FragmentHomeBinding).text -> navigateToCategory()
        }
    }


    private fun navigateToExam() {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToExam())
    }


    private fun navigateToCategory() {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToCategory())

    }
}
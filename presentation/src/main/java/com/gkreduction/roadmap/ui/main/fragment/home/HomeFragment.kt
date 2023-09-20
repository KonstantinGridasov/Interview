package com.gkreduction.roadmap.ui.main.fragment.home

import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentHomeBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
import com.gkreduction.roadmap.ui.main.MainActivity

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
            (binding as FragmentHomeBinding).subItemText -> viewModel?.fetchRoadmaps()
            (binding as FragmentHomeBinding).subItemQuestion -> viewModel?.fetchQA()
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
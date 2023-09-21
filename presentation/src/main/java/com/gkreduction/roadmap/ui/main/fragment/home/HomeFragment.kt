package com.gkreduction.roadmap.ui.main.fragment.home

import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentHomeBinding
import com.gkreduction.roadmap.ui.base.BaseFragment

class HomeFragment :
    BaseFragment<HomeViewModel>(
        R.layout.fragment_home,
        HomeViewModel::class.java
    ), View.OnClickListener {


    override fun onStart() {
        super.onStart()
        (binding as FragmentHomeBinding).listener = this
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getRoadmapsFromDb()

    }

    override fun onClick(p0: View?) {
        when (p0) {
            (binding as FragmentHomeBinding).subItemText -> viewModel?.fetchRoadmaps()
            (binding as FragmentHomeBinding).subItemQuestion -> viewModel?.fetchQA()
        }
    }


    //region Navigate

    private fun onRoadmapClick(long: Long) {
        Log.d(" initAdapters(it)", "onRoadmapClick= $long")
    }

    private fun onQuestionClick(long: Long) {
        Log.d("initAdapters", "onQuestionClick= $long")
    }

    private fun onTheoryClick(long: Long) {
        Log.d("initAdapters", "onTheoryClick= $long")
    }

    private fun navigateToExam() {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToExam())
    }


    private fun navigateToCategory() {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToCategory())

    }
    //endregion
}
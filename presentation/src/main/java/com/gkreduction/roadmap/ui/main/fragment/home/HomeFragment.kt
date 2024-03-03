package com.gkreduction.roadmap.ui.main.fragment.home

import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.gkreduction.domain.entity.ItemRoadmap
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentHomeBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
import com.gkreduction.roadmap.ui.main.fragment.home.adapter.RoadmapAdapter

class HomeFragment :
    BaseFragment<HomeViewModel>(
        R.layout.fragment_home,
        HomeViewModel::class.java
    ), View.OnClickListener {

    private var roadmapAdapter: RoadmapAdapter? = null

    override fun initialize() {
        super.initialize()
        (binding as FragmentHomeBinding).listener = this
        initAdapters()
        initObservers()
        viewModel?.getRoadmapsFromDb()
    }


    override fun onClick(p0: View?) {
        when (p0) {
            (binding as FragmentHomeBinding).subItemText -> viewModel?.update { viewModel?.fetchRoadmaps() }
            (binding as FragmentHomeBinding).subItemQuestion -> viewModel?.update { viewModel?.fetchQA() }
        }
    }


    private fun initAdapters() {
        roadmapAdapter = RoadmapAdapter(
            onRoadmapClick = { onRoadmapClick(it) },
            onTheoryClick = { onTheoryClick(it) },
            onQuestionClick = { onQuestionClick(it) })

        (binding as FragmentHomeBinding).rvRoadmaps.adapter = roadmapAdapter
    }


    private fun initObservers() {
        activity?.let {
            viewModel?.roadmaps?.observe(it) { items ->
                roadmapAdapter?.updateItems(items)
            }
        }
        activity?.let {
            viewModel?.availableServer?.observe(it) { status ->
                if (status) viewModel?.command?.invoke()
            }
        }

    }
    //region Navigate

    private fun onRoadmapClick(item: ItemRoadmap) {
        navigateToRoadmap(item.id)
    }

    private fun onQuestionClick(item: ItemRoadmap) {
        navigateToExam(item)
    }

    private fun onTheoryClick(item: ItemRoadmap) {
        navigateToTheory(item)
    }

    private fun navigateToExam(item: ItemRoadmap) {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToExam(item))
    }


    private fun navigateToTheory(item: ItemRoadmap) {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToTheory(item))

    }

    private fun navigateToRoadmap(id: Long) {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToRoadmap(id))
    }
    //endregion
}
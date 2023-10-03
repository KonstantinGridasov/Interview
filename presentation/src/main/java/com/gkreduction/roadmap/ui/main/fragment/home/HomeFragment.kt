package com.gkreduction.roadmap.ui.main.fragment.home

import android.view.View
import androidx.navigation.findNavController
import com.gkreduction.domain.entity.BaseItem
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
            (binding as FragmentHomeBinding).subItemText -> viewModel?.fetchRoadmaps()
            (binding as FragmentHomeBinding).subItemQuestion -> viewModel?.fetchQA()
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

    }
    //region Navigate

    private fun onRoadmapClick(item: BaseItem) {
        navigateToRoadmap(item.id)
    }

    private fun onQuestionClick(item: BaseItem) {
        navigateToExam(item)
    }

    private fun onTheoryClick(item: BaseItem) {
        navigateToTheory(item)
    }

    private fun navigateToExam(item: BaseItem) {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToExam(item))
    }


    private fun navigateToTheory(item: BaseItem) {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToTheory(item))

    }

    private fun navigateToRoadmap(id: Long) {
        view?.findNavController()
            ?.navigate(HomeFragmentDirections.homeToRoadmap(id))
    }
    //endregion
}
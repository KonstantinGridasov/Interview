package com.gkreduction.roadmap.ui.main.fragment.roadmap

import androidx.navigation.findNavController
import com.gkreduction.domain.entity.ItemRoadmap
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentRoadmapBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
import com.gkreduction.roadmap.ui.dialog.bottomSheet.BottomSheet
import com.gkreduction.roadmap.ui.main.MainActivity
import com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter.SectionAdapter


class RoadmapFragment : BaseFragment<RoadmapViewModel>(
    R.layout.fragment_roadmap,
    RoadmapViewModel::class.java
) {
    override fun onStart() {
        super.onStart()
        val args = RoadmapFragmentArgs.fromBundle(requireArguments())
        viewModel?.getRoadmapFull(args.id)
        initListeners()
    }


    private fun initListeners() {
        activity?.let {
            viewModel?.list?.observe(it) { item ->
                val adapter =
                    SectionAdapter(
                        clickListener = { it -> navigateToTheory(it) },
                        longListener = { it -> showBottomDialog(it) },
                        item
                    )
                (binding as FragmentRoadmapBinding).rvRoadmaps.adapter = adapter
                (it as MainActivity).setToolbarName(it.resources.getString(R.string.roadmap))
            }
        }

    }


    private fun showBottomDialog(item: ItemRoadmap) {
        activity?.let { act ->
            val dialog = BottomSheet().setParams(
                item,
                { navigateToList(item) },
                { navigateToTheory(item) },
                { navigateToExam(item) }
            )
            dialog.show(act.supportFragmentManager, "")
        }

    }

    private fun navigateToList(item: ItemRoadmap) {
        view?.findNavController()
            ?.navigate(RoadmapFragmentDirections.roadmapToList(item))

    }

    private fun navigateToExam(item: ItemRoadmap) {
        view?.findNavController()
            ?.navigate(RoadmapFragmentDirections.roadmapToExam(item))
    }

    private fun navigateToTheory(item: ItemRoadmap) {
        view?.findNavController()
            ?.navigate(RoadmapFragmentDirections.roadmapToTheory(item))

    }
}
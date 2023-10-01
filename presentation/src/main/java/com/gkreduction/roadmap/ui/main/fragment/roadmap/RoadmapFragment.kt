package com.gkreduction.roadmap.ui.main.fragment.roadmap

import androidx.navigation.findNavController
import com.gkreduction.domain.entity.BaseItem
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentRoadmapBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
import com.gkreduction.roadmap.ui.main.MainActivity
import com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter.OnSectionListener
import com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter.OnSubtopicListener
import com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter.OnTopicListener
import com.gkreduction.roadmap.ui.main.fragment.roadmap.adapter.SectionAdapter


class RoadmapFragment : BaseFragment<RoadmapViewModel>(
    R.layout.fragment_roadmap,
    RoadmapViewModel::class.java
), OnSectionListener, OnSubtopicListener, OnTopicListener {
    lateinit var adapter: SectionAdapter
    override fun onStart() {
        super.onStart()

        val args = RoadmapFragmentArgs.fromBundle(requireArguments())
        viewModel?.getRoadmapFull(args.id)

        initAdapters()
        initObservers()
    }


    private fun initAdapters() {
        adapter = SectionAdapter(this, this, this)
        (binding as FragmentRoadmapBinding).rvRoadmaps.adapter = adapter
    }


    private fun initObservers() {
        activity?.let {
            viewModel?.roadmap?.observe(it) { item ->
                adapter.updateItems(item)
                (it as MainActivity).setToolbarName(item.name)
            }
        }

    }

    override fun onSectionClick(item: BaseItem) {
        navigateToTheory(item)
    }

    override fun onSectionLongClick(item: BaseItem) {
        showBottomDialog(item)
    }

    override fun onSubtopicClick(item: BaseItem) {
        navigateToTheory(item)
    }

    override fun onSubtopicLongClick(item: BaseItem) {
        showBottomDialog(item)
    }

    override fun onTopicClick(item: BaseItem) {
        navigateToTheory(item)
    }

    override fun onTopicLongClick(item: BaseItem) {
        showBottomDialog(item)
    }


    private fun showBottomDialog(item: BaseItem) {
        activity?.let {
            if (it is MainActivity)
                it.showDialogBottom(
                    item,
                    { navigateToList(item) },
                    { navigateToTheory(item) },
                    { navigateToExam(item) }
                )
        }
    }

    private fun navigateToList(item: BaseItem) {
        view?.findNavController()
            ?.navigate(RoadmapFragmentDirections.roadmapToList(item))

    }

    private fun navigateToExam(item: BaseItem) {

    }

    private fun navigateToTheory(item: BaseItem) {
        view?.findNavController()
            ?.navigate(RoadmapFragmentDirections.roadmapToTheory(item))

    }
}
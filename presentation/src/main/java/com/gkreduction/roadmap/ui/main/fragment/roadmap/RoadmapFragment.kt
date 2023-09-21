package com.gkreduction.roadmap.ui.main.fragment.roadmap

import android.util.Log
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentRoadmapBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
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
            }
        }

    }

    override fun onSectionClick(id: Long) {
        Log.d("initAdapters", "onSectionClick= $id")
    }

    override fun onSubtopicClick(id: Long) {
        Log.d("initAdapters", "onSubtopicClick= $id")
    }

    override fun onTopicClick(id: Long) {
        Log.d("initAdapters", "onTopicClick= $id")
    }


}
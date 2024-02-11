package com.gkreduction.roadmap.ui.main.fragment.list_title

import androidx.navigation.findNavController
import com.gkreduction.domain.entity.BaseItem
import com.gkreduction.domain.entity.ItemRoadmap
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentListTitleBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
import com.gkreduction.roadmap.ui.main.MainActivity
import com.gkreduction.roadmap.ui.main.fragment.list_title.adapter.ListTitleAdapter
import com.gkreduction.roadmap.ui.main.fragment.list_title.adapter.OnTitleItemListener


class ListTitleFragment : BaseFragment<ListTitleViewModel>(
    R.layout.fragment_list_title,
    ListTitleViewModel::class.java
), OnTitleItemListener {
    lateinit var adapter: ListTitleAdapter
    private lateinit var item: ItemRoadmap

    override fun onStart() {
        super.onStart()
        item = ListTitleFragmentArgs.fromBundle(requireArguments()).item
        viewModel?.getQuestionsById(item)
        setToolbarName(item.name)
        initAdapters()
        initObservers()
    }

    private fun setToolbarName(name: String) {
        activity?.let {
            (it as MainActivity).setToolbarName(name)
        }

    }


    private fun initAdapters() {
        adapter = ListTitleAdapter(this)
        (binding as FragmentListTitleBinding).rvListItem.adapter = adapter
    }


    private fun initObservers() {
        activity?.let {
            viewModel?.roadmap?.observe(it) { item ->
                adapter.updateItems(item)
            }
        }

    }

    override fun onTitleClick(id: Long) {
        view?.findNavController()
            ?.navigate(ListTitleFragmentDirections.listToTheory(item,id))
    }


}
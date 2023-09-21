package com.gkreduction.roadmap.ui.main.fragment.list

import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.FragmentListQuestionBinding
import com.gkreduction.roadmap.ui.base.BaseFragment
import com.gkreduction.roadmap.ui.main.MainActivity
import com.gkreduction.roadmap.ui.main.fragment.list.adapter.ListQuestionAdapter
import com.gkreduction.roadmap.ui.main.fragment.list.adapter.OnTitleItemListener


class ListQuestionFragment : BaseFragment<ListQuestionViewModel>(
    R.layout.fragment_list_question,
    ListQuestionViewModel::class.java
), OnTitleItemListener {
    lateinit var adapter: ListQuestionAdapter
    override fun onStart() {
        super.onStart()

        val item = ListQuestionFragmentArgs.fromBundle(requireArguments()).item
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
        adapter = ListQuestionAdapter(this)
        (binding as FragmentListQuestionBinding).rvListItem.adapter = adapter
    }


    private fun initObservers() {
        activity?.let {
            viewModel?.roadmap?.observe(it) { item ->
                adapter.updateItems(item)
            }
        }

    }

    override fun onTitleClick(id: Long) {
    }


}
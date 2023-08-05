package com.gkreduction.interview.ui.main.fragmnet.category

import androidx.navigation.findNavController
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.FragmentCategoryBinding
import com.gkreduction.interview.ui.base.BaseFragment
import com.gkreduction.interview.ui.main.MainActivity
import com.gkreduction.interview.ui.main.fragmnet.category.adapter.AdapterCategory

class CategoryFragment :
    BaseFragment<CategoryViewModel>(
        R.layout.fragment_category,
        CategoryViewModel::class.java
    ), AdapterCategory.ListenerList {


    override fun onStart() {
        super.onStart()
        (binding as FragmentCategoryBinding).viewModel = viewModel
        (binding as FragmentCategoryBinding).listener = this

        activity?.let {
            if (it is MainActivity) {
                viewModel?.setCategories(it.data)
            }
        }
        activity?.let {
            if (it is MainActivity)
                it.setToolbarName(it.resources.getString(R.string.category))
        }
    }



    override fun onCategoryClick(type: String) {
        view?.findNavController()
            ?.navigate(CategoryFragmentDirections.actionCategoryToQuestion3(type))
    }
}
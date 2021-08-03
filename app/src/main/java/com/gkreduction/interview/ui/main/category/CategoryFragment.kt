package com.gkreduction.interview.ui.main.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.FragmentCategoryBinding
import com.gkreduction.interview.ui.main.MainActivity
import com.gkreduction.interview.utils.lazyThreadSafetyNone
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CategoryFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binder: FragmentCategoryBinding

    private val viewModel by lazyThreadSafetyNone {
        activity?.let {
            ViewModelProvider(
                it,
                viewModelFactory
            ).get(CategoryViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        binder.viewModel = viewModel
        binder.fragment = this
        return binder.root
    }

    override fun onStart() {
        super.onStart()
        initCat()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel!!.readFile()
    }


    private fun initCat() {
        activity?.let {
            viewModel!!.dataInfo.observe(it, Observer { list ->
                if (it is MainActivity)
                    it.data = list
            })
        }
//        activity?.let {
//            if (it is MainActivity)
//                viewModel!!.initCategory(it.data!!)
//        }
    }

    fun navigateToQuestion(cat: String) {
        view?.findNavController()?.navigate(
            CategoryFragmentDirections.actionCategoryToQuestion3(cat)
        )

    }
}
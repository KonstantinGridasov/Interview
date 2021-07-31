package com.gkreduction.interview.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gkreduction.interview.R
import com.gkreduction.interview.utils.lazyThreadSafetyNone
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    private lateinit var binder: FragmentEventListBinding

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


}
package com.gkreduction.interview.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.ActivityMainBinding
import com.gkreduction.interview.entity.DataInfo
import com.gkreduction.interview.utils.lazyThreadSafetyNone
import com.gkreduction.interview.utils.readerJson
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    var data: List<DataInfo>? = null
    private lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = this.findNavController(R.id.nav_host_fragment)

        data = readerJson(this)
        initListener()
    }

    private fun initListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.setImageByDestination(destinationId = destination.id)
        }
    }

    fun setToolbarName(name: String) {
        binding.toolbar.setTextName(name)
    }


}
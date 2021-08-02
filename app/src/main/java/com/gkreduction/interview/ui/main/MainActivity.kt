package com.gkreduction.interview.ui.main

import android.os.Bundle
import android.view.Window.FEATURE_NO_TITLE
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.ActivityMainBinding
import com.gkreduction.interview.entity.DataInfo
import com.gkreduction.interview.utils.lazyThreadSafetyNone
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    var data: List<DataInfo>? = null
    private var navController: NavController? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        requestWindowFeature(FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        viewModel.readFile()
//        viewModel.dataInfo.observe(this, Observer { list -> data = list })

        navController = this.findNavController(R.id.nav_host_fragment)
    }


}
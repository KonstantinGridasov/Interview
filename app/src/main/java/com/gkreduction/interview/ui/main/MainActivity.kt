package com.gkreduction.interview.ui.main

import android.os.Bundle
import android.view.Window.FEATURE_NO_TITLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.gkreduction.interview.R
import com.gkreduction.interview.databinding.ActivityMainBinding
import com.gkreduction.interview.utils.lazyThreadSafetyNone
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

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
        binding.viewModel = this.viewModel

    }


}
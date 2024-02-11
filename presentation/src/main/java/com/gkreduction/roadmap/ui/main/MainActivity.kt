package com.gkreduction.roadmap.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gkreduction.roadmap.R
import com.gkreduction.roadmap.databinding.ActivityMainBinding
import com.gkreduction.roadmap.ui.dialog.finish.FinishDialog
import com.gkreduction.roadmap.ui.dialog.help.HelpDialog
import com.gkreduction.roadmap.utils.lazyThreadSafetyNone
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
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
        initListener()

    }

    private fun initListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.setVisibilityToolbar(destinationId = destination.id)
        }
        setToolbarNavigation()
    }

    fun setToolbarName(name: String) {
        binding.toolbar.setTextName(name)
    }

    fun setToolbarNavigation() {
        binding.toolbar.setListenerToolbar { navController.navigateUp() }
    }

    fun showDialogHelp(item: String, listener: () -> Unit) {
        val dialog = HelpDialog().setParams(item, listener)
        dialog.show(supportFragmentManager, "")
    }


    fun onBack() {
        navController.navigateUp()
    }

    fun showDialogFinish(status: Boolean, listenerFinish: () -> Unit, listenerRestart: () -> Unit) {
        val dialog =
            FinishDialog().setParams(
                status,
                listenerFinish = listenerFinish,
                listenerRestart = listenerRestart
            )
        dialog.show(supportFragmentManager, "")
    }


}
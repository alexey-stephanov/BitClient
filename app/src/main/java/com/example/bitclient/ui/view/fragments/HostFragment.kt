package com.example.bitclient.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.bitclient.R
import com.example.bitclient.databinding.FragmentHostBinding
import com.example.bitclient.ui.bottomnavigation.BottomNavigationBehavior
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HostFragment : Fragment(R.layout.fragment_host) {

    private val binding by viewBinding(FragmentHostBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.settingsFragment, R.id.accountFragment))

        with(binding) {
            toolbar.setupWithNavController(navController, appBarConfiguration)
            bottomNavigationView.setupWithNavController(navController)
        }
    }
}
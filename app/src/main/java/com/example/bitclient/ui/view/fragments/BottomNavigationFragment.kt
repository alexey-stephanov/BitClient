package com.example.bitclient.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.bitclient.R
import com.example.bitclient.databinding.FragmentBottomNavigationBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding

class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {

    private val binding by viewBinding(FragmentBottomNavigationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
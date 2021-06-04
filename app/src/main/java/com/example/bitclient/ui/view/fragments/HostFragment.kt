package com.example.bitclient.ui.view.fragments

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.bitclient.R
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import com.example.bitclient.databinding.FragmentHostBinding
import com.example.bitclient.ui.appbars.AppBarsStateHandler
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import timber.log.Timber

class HostFragment : Fragment(R.layout.fragment_host), AppBarsStateHandler {

    private val binding by viewBinding(FragmentHostBinding::bind)

    private var offsetAnimator: ValueAnimator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.settingsFragment, R.id.accountFragment))

        with(binding) {
            toolbar.setupWithNavController(navController, appBarConfiguration)
            bottomNavigationView.setupWithNavController(navController)
        }

        startConnectionChecking()
    }

    private fun startConnectionChecking() {
        NetworkStatus.observe(viewLifecycleOwner, { isAvailable ->
            TransitionManager.beginDelayedTransition(binding.root, Slide(Gravity.TOP))
            binding.textViewHostNoInternet.isVisible = !isAvailable
        })
    }

    override fun show() {
            binding.appBarLayout.setExpanded(true)
            if(binding.bottomNavigationView.translationY > 0) {
                if (offsetAnimator == null) {
                    offsetAnimator = ValueAnimator().apply {
                        interpolator = DecelerateInterpolator()
                        duration = 150L
                    }

                    offsetAnimator?.addUpdateListener {
                        binding.bottomNavigationView.translationY = it.animatedValue as Float
                    }
                } else {
                    offsetAnimator?.cancel()
                }
                offsetAnimator?.setFloatValues(168f, 0f)
                offsetAnimator?.start()
            }
    }

    override fun onStop() {
        offsetAnimator?.cancel()
        super.onStop()
    }

    override fun onDestroy() {
        offsetAnimator?.removeAllListeners()
        super.onDestroy()
    }
}
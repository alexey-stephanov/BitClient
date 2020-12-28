package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.NetworkLiveData
import com.example.bitclient.databinding.FragmentSettingsBinding
import com.example.bitclient.di.UserComponentManager
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.SettingsViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding by viewBinding(FragmentSettingsBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var settingsViewModel: SettingsViewModel

    private lateinit var userComponentManager: UserComponentManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        userComponentManager = (requireActivity().application as BitClientApp).appComponent.userComponentManager()
        userComponentManager.userComponent!!.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsViewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.buttonSettingsLogout.setOnClickListener {
            settingsViewModel.logout()
            userComponentManager.removeComponent()
        }
    }

    override fun onResume() {
        super.onResume()

        startConnectionChecking()
    }

    private fun startConnectionChecking() {
        NetworkLiveData.observe(this, {
            if (it) {
                if (binding.textViewSettingsNoInternet.isVisible)
                    binding.textViewSettingsNoInternet.visibility = View.GONE
            } else {
                binding.textViewSettingsNoInternet.visibility = View.VISIBLE
            }
        })
    }
}
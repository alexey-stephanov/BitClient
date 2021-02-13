package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.di.UserSubcomponentManager
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.databinding.FragmentSettingsBinding
import com.example.bitclient.ui.view.MainActivity
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.SettingsViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding by viewBinding(FragmentSettingsBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val settingsViewModel: SettingsViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    private lateinit var userSubcomponentManager: UserSubcomponentManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        userSubcomponentManager =
            (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager()
        userSubcomponentManager.userSubcomponent?.settingsSubcomponent()?.create()?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        networkConnectivityManager.startConnectionChecking(
            viewLifecycleOwner,
            binding.root,
            binding.textViewSettingsNoInternet
        )
        binding.buttonSettingsLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        settingsViewModel.logout()
        userSubcomponentManager.removeComponent()
        requireActivity().finish()
        requireActivity().overridePendingTransition(0, 0)
        val intent = Intent(requireContext(), MainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        requireActivity().overridePendingTransition(0, 0)
    }
}
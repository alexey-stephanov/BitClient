package com.example.bitclient.ui.view.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.databinding.FragmentSettingsBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.SettingsViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding by viewBinding(FragmentSettingsBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var settingsViewModel: SettingsViewModel

    private lateinit var userManager: UserManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        userManager = (requireActivity().application as BitClientApp).appComponent.userManager()
        userManager.userComponent!!.inject(this)
    }
}
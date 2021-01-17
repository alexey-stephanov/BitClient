package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel
import com.example.bitclient.databinding.FragmentAccountBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.AccountViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class AccountFragment : Fragment(R.layout.fragment_account) {

    private val binding by viewBinding(FragmentAccountBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val accountViewModel: AccountViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.accountComponent()?.create()?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startConnectionChecking()
        getUserInfo()
    }

    private fun getUserInfo() {
        accountViewModel.liveUserModel.observe(viewLifecycleOwner, { userModel ->
            setupView(userModel)
        })
    }

    private fun setupView(userModel: UserModel) {
        binding.progressBarAccountLoading.isGone = true
        binding.imageViewAccountAvatar.setImageURI(userModel.links.avatar.href)
        binding.textViewAccountDisplayName.text = userModel.displayName
        binding.textViewAccountUsername.text = userModel.username
    }

    private fun startConnectionChecking() {
        NetworkStatus.observe(viewLifecycleOwner, { isAvailable ->
            TransitionManager.beginDelayedTransition(binding.root, Slide())
            binding.textViewAccountNoInternet.isVisible = !isAvailable
        })
    }
}
package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.TokenManager
import com.example.bitclient.data.network.TokenStatus
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

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userComponentManager().userComponent?.accountComponent()?.create()?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startConnectionChecking()
        observeTokenStatus()
    }

    private fun observeTokenStatus() {
        val userTokensObserver = Observer<TokenStatus> { tokenStatus ->
            when(tokenStatus) {
                is TokenStatus.Loading -> {
                    Log.e("TOKEN_LOADING", "Pls wait.")
                }
                is TokenStatus.Ready -> {
                    getUserInfo()
                }
                is TokenStatus.Error -> {
                    Log.e("TOKEN_ERROR", "Some problems with token.")
                }
            }
        }
        tokenManager.tokenStatusLiveData.observe(viewLifecycleOwner, userTokensObserver)
    }

    private fun getUserInfo() {
        binding.progressBarAccountLoading.isVisible = true
        accountViewModel.loadUserInfo()
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
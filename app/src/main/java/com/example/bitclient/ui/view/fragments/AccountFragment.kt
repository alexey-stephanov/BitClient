package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.models.usermodel.UserModel
import com.example.bitclient.data.network.NetworkLiveData
import com.example.bitclient.databinding.FragmentAccountBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.AccountViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class AccountFragment : Fragment(R.layout.fragment_account) {

    private val binding by viewBinding(FragmentAccountBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var accountViewModel: AccountViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userComponentManager().userComponent!!.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountViewModel = ViewModelProvider(this, viewModelFactory).get(AccountViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userModelObserver = Observer<UserModel> { userModel ->
            binding.imageViewAccountAvatar.setImageURI(userModel.links.avatar.href)
            binding.textViewAccountDisplayName.text = userModel.displayName
            binding.textViewAccountUsername.text = userModel.username
        }
        accountViewModel.liveUserModel.observe(viewLifecycleOwner, userModelObserver)
        startConnectionChecking()
    }

    private fun startConnectionChecking() {
        NetworkLiveData.observe(viewLifecycleOwner, { isAvailable ->
            TransitionManager.beginDelayedTransition(binding.root, Slide())
            binding.textViewAccountNoInternet.isVisible = !isAvailable
        })
    }
}
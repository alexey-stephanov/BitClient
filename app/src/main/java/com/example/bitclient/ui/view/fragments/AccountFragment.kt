package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

    override fun onResume() {
        super.onResume()

        val userModelObserver = Observer<UserModel> {
            binding.imageViewAccountAvatar.setImageURI(it.links.avatar.href)
            binding.textViewAccountDisplayName.text = it.displayName
            binding.textViewAccountUsername.text = it.username
        }
        accountViewModel.liveUserModel.observe(viewLifecycleOwner, userModelObserver)
        startConnectionChecking()
    }

    private fun startConnectionChecking() {
        NetworkLiveData.observe(this, {
            if (it) {
                if (binding.textViewAccountNoInternet.isVisible)
                    binding.textViewAccountNoInternet.visibility = View.GONE
            } else {
                binding.textViewAccountNoInternet.visibility = View.VISIBLE
            }
        })
    }
}
package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
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
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.accountSubcomponent()
            ?.create()?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networkConnectivityManager.startConnectionChecking(
            viewLifecycleOwner,
            binding.root,
            binding.textViewAccountNoInternet
        )
        getUserInfo()
        setupRefreshLayout()
        startNetworkChecking()
    }

    private fun getUserInfo() {
        accountViewModel.liveAccountModel.observe(viewLifecycleOwner, { userModel ->
            setupView(userModel)
        })
    }

    private fun setupView(accountDbModel: AccountDbModel) {
        binding.progressBarAccountLoading.isGone = true
        binding.imageViewAccountAvatar.setImageURI(accountDbModel.avatarLink)
        binding.textViewAccountDisplayName.text = accountDbModel.displayName
        binding.textViewAccountUsername.text = accountDbModel.username
    }

    private fun setupRefreshLayout() {
        binding.swipeLayoutAccountRefresh.setOnRefreshListener {
            accountViewModel.loadData()
            binding.swipeLayoutAccountRefresh.isRefreshing = false
        }
    }

    private fun startNetworkChecking() {
        accountViewModel.networkStatus.observe(viewLifecycleOwner, {
            Toast.makeText(
                requireContext(),
                getString(R.string.load_error_message),
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}
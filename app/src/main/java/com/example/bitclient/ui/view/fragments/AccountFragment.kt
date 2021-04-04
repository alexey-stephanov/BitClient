package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.databinding.FragmentAccountBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.viewmodels.AccountViewModel
import com.example.bitclient.viewmodels.factories.ViewModelFactory
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
        observeAccountData()
        setupRefreshLayout()
    }

    private fun observeAccountData() {
        accountViewModel.accountData.observe(viewLifecycleOwner, { accountModel ->
            setupView(accountModel)
        })
    }

    private fun setupView(accountDbModel: AccountDbModel) {
        with(binding) {
            if (containerAccountPlaceholder.isShimmerVisible) {
                containerAccountPlaceholder.stopShimmer()
                containerAccountPlaceholder.visibility = View.GONE
            }
            imageViewAccountAvatar.setImageURI(accountDbModel.avatarLink)
            textViewAccountDisplayName.text = accountDbModel.displayName
            textViewAccountUsername.text = accountDbModel.username
            textViewCreationDay.text = getString(R.string.created_on, accountDbModel.createdOn)
        }
    }

    private fun setupRefreshLayout() {
        binding.swipeLayoutAccountRefresh.setOnRefreshListener {
            startExceptionsChecking()
            accountViewModel.loadData()
            binding.swipeLayoutAccountRefresh.isRefreshing = false
        }
    }

    private fun startExceptionsChecking() {
        accountViewModel.exceptionMessage.observe(viewLifecycleOwner, { exceptionMessage ->
            Toast.makeText(
                requireContext(),
                exceptionMessage,
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}
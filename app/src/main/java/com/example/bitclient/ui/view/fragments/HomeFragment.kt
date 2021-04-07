package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.databinding.FragmentHomeBinding
import com.example.bitclient.ui.appbars.getAppBarsStateHandler
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.viewmodels.HomeViewModel
import com.example.bitclient.viewmodels.factories.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.inject(
            this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAccountData()

    }

    private fun observeAccountData() {
        homeViewModel.accountData.observe(viewLifecycleOwner, { accountData ->
            setupView(accountData)
        })
    }

    private fun setupView(accountData: AccountDbModel) {
        with(binding) {
            if (containerHomePlaceholder.isShimmerVisible) {
                containerHomePlaceholder.stopShimmer()
                containerHomePlaceholder.visibility = View.GONE
            }
            groupHomeUserSection.visibility = View.VISIBLE
            textViewHomeGreetings.text = getString(R.string.home_greetings, accountData.displayName)
            buttonHomeRepositories.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToRepositoriesFragment(accountData.workspaceId)
                findNavController().navigate(action)
            }
            buttonHomeOrganizations.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToOrganizationsFragment(accountData.accountId)
                findNavController().navigate(action)
            }
        }
    }
}
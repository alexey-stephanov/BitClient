package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.databinding.FragmentBranchesBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.BranchesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class BranchesFragment : Fragment(R.layout.fragment_branches) {

    private val binding by viewBinding(FragmentBranchesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val branchesViewModel: BranchesViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.repositoriesComponent()
            ?.create()?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        networkConnectivityManager.startConnectionChecking(
            viewLifecycleOwner,
            binding.root,
            binding.textViewBranchesNoInternet
        )
    }
}
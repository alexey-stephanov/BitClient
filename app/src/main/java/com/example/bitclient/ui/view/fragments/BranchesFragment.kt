package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchModel
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.data.network.requests.UserDataRepository
import com.example.bitclient.databinding.BranchItemBinding
import com.example.bitclient.databinding.FragmentBranchesBinding
import com.example.bitclient.ui.recyclerview.OnItemClickListener
import com.example.bitclient.ui.recyclerview.PaginatedListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.BranchesViewModel
import com.example.bitclient.ui.viewmodels.BranchesViewModelFactory
import javax.inject.Inject

class BranchesFragment : PaginatedFragment<BranchModel>() {

    private val binding by viewBinding(FragmentBranchesBinding::bind)

    private val args: BranchesFragmentArgs by navArgs()

    @Inject
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    @Inject
    lateinit var userDataRepository: UserDataRepository

    override val viewModel: BranchesViewModel by viewModels {
        BranchesViewModelFactory(userDataRepository, args.workspaceId!!, args.repositoryId!!)
    }

    override val paginatedListAdapter: PaginatedListAdapter<BranchModel> =
        object : PaginatedListAdapter<BranchModel>(OnItemClickListener { },
            { inflater, parent ->
                BranchItemBinding.inflate(inflater, parent, false)
            }
        ) {
            override fun getLayoutId(position: Int, obj: BranchModel): Int =
                R.layout.branch_item
        }

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

    override fun getLayoutResId(): Int =
        R.layout.fragment_branches

    override fun getRecyclerView(): RecyclerView = binding.recyclerViewBranchesBranchesList
}
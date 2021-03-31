package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchModel
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.databinding.BranchItemBinding
import com.example.bitclient.databinding.FragmentBranchesBinding
import com.example.bitclient.ui.recyclerview.OnItemClickListener
import com.example.bitclient.ui.recyclerview.PaginatedListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.viewmodels.BranchesViewModel
import com.example.bitclient.viewmodels.BranchesViewModelFactory
import javax.inject.Inject

class BranchesFragment : PaginatedFragment<BranchModel, BranchDbModel>() {

    private val binding by viewBinding(FragmentBranchesBinding::bind)

    private val args: BranchesFragmentArgs by navArgs()

    @Inject
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    @Inject
    lateinit var branchesViewModelFactory: BranchesViewModelFactory

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    @ExperimentalPagingApi
    override val viewModel: BranchesViewModel by lazy {
        branchesViewModelFactory.create(args.workspaceId!!, args.repositoryId!!)
    }

    override val paginatedListAdapter: PaginatedListAdapter<BranchDbModel> =
        object : PaginatedListAdapter<BranchDbModel>(OnItemClickListener { data ->
            val action =
                BranchesFragmentDirections.actionBranchesFragmentToCommitsFragment(
                    args.workspaceId,
                    args.repositoryId,
                    data.branchName
                )
            findNavController().navigate(action)
        },
            { inflater, parent ->
                BranchItemBinding.inflate(inflater, parent, false)
            }
        ) {
            override fun getLayoutId(position: Int, obj: BranchDbModel): Int =
                R.layout.branch_item
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.repositoriesSubcomponentManager()?.repositoriesSubcomponent
            ?.branchesSubcomponentManager()?.branchesSubcomponent?.inject(this)
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarBranchesActionbar) {
            setNavigationIcon(R.drawable.ic_left_arrow)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }

        networkConnectivityManager.startConnectionChecking(
            viewLifecycleOwner,
            binding.root,
            binding.textViewBranchesNoInternet
        )
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_branches

    override fun getRecyclerView(): RecyclerView {
        binding.recyclerViewBranchesBranchesList.addItemDecoration(itemDecoration)
        return binding.recyclerViewBranchesBranchesList
    }
}
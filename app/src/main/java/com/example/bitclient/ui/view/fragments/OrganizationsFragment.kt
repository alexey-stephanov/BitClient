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
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.WorkspaceModel
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.databinding.FragmentOrganizationsBinding
import com.example.bitclient.databinding.OrganizationItemBinding
import com.example.bitclient.ui.recyclerview.OnItemClickListener
import com.example.bitclient.ui.recyclerview.PaginatedListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.viewmodels.OrganizationsViewModel
import com.example.bitclient.viewmodels.factories.OrganizationsViewModelFactory
import com.facebook.shimmer.ShimmerFrameLayout
import javax.inject.Inject

class OrganizationsFragment : PaginatedFragment<WorkspaceModel, WorkspaceDbModel>() {

    private val binding by viewBinding(FragmentOrganizationsBinding::bind)

    private val args: OrganizationsFragmentArgs by navArgs()

    @Inject
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    @Inject
    lateinit var organizationsViewModelFactory: OrganizationsViewModelFactory

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    @ExperimentalPagingApi
    override val viewModel: OrganizationsViewModel by lazy {
        organizationsViewModelFactory.create(args.accountId!!)
    }

    override val paginatedListAdapter: PaginatedListAdapter<WorkspaceDbModel> =
        object : PaginatedListAdapter<WorkspaceDbModel>(
            OnItemClickListener { data ->
                val action =
                    OrganizationsFragmentDirections.actionOrganizationsFragmentToRepositoriesFragment(
                        data.workspaceId
                    )
                findNavController().navigate(action)
            },
            { inflater, parent ->
                OrganizationItemBinding.inflate(inflater, parent, false)
            }
        ) {
            override fun getLayoutId(position: Int, obj: WorkspaceDbModel): Int =
                R.layout.organization_item
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.organizationsSubcomponent()
            ?.create()?.inject(this)
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarOrganizationsActionbar) {
            setNavigationIcon(R.drawable.ic_left_arrow)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }

        networkConnectivityManager.startConnectionChecking(
            viewLifecycleOwner,
            binding.root,
            binding.textViewOrganizationsNoInternet
        )
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_organizations

    override fun getShimmerFrameLayout(): ShimmerFrameLayout =
        binding.containerOrganizationsPlaceholder

    override fun getRecyclerView(): RecyclerView {
        binding.recyclerViewOrganizationsWorkspacesList.addItemDecoration(itemDecoration)
        return binding.recyclerViewOrganizationsWorkspacesList
    }
}
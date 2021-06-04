package com.example.bitclient.ui.view.fragments

import android.content.Context
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.databinding.FragmentRepositoriesBinding
import com.example.bitclient.databinding.RepositoryItemBinding
import com.example.bitclient.ui.recyclerview.listeners.OnItemClickListener
import com.example.bitclient.ui.recyclerview.adapters.PaginatedListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.viewmodels.RepositoriesViewModel
import com.example.bitclient.viewmodels.factories.RepositoriesViewModelFactory
import com.facebook.shimmer.ShimmerFrameLayout
import javax.inject.Inject

class RepositoriesFragment : PaginatedFragment<RepositoryModel, RepositoryDbModel>() {

    private val binding by viewBinding(FragmentRepositoriesBinding::bind)

    private val args: RepositoriesFragmentArgs by navArgs()

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    @Inject
    lateinit var repositoriesViewModelFactory: RepositoriesViewModelFactory

    @ExperimentalPagingApi
    override val viewModel: RepositoriesViewModel by lazy {
        repositoriesViewModelFactory.create(args.workspaceId!!)
    }

    @ExperimentalPagingApi
    override val paginatedListAdapter: PaginatedListAdapter<RepositoryDbModel> =
        object : PaginatedListAdapter<RepositoryDbModel>(OnItemClickListener { data ->
            val action =
                RepositoriesFragmentDirections.actionRepositoriesFragmentToBranchesFragment(
                    data.repositoryOwnerId,
                    data.repositoryId
                )
            findNavController().navigate(action)
        }, { inflater, viewGroup ->
            RepositoryItemBinding.inflate(inflater, viewGroup, false)
        }) {
            override fun getLayoutId(position: Int, obj: RepositoryDbModel): Int =
                R.layout.repository_item
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.repositoriesSubcomponentManager()?.repositoriesSubcomponent
            ?.inject(this)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_repositories

    override fun bindShimmerFrameLayout(): ShimmerFrameLayout =
        binding.containerRepositoriesPlaceholder

    override fun bindRecyclerView(): RecyclerView {
        binding.recyclerViewRepositoriesReposList.addItemDecoration(itemDecoration)
        return binding.recyclerViewRepositoriesReposList
    }
}
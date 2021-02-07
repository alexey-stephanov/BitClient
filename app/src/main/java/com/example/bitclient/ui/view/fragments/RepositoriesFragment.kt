package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.databinding.FragmentRepositoriesBinding
import com.example.bitclient.databinding.RepositoryItemBinding
import com.example.bitclient.ui.recyclerview.OnItemClickListener
import com.example.bitclient.ui.recyclerview.PaginatedListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.RepositoriesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class RepositoriesFragment : PaginatedFragment<RepositoryModel, RepositoryDbModel>() {

    private val binding by viewBinding(FragmentRepositoriesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    @ExperimentalPagingApi
    override val paginatedListAdapter: PaginatedListAdapter<RepositoryDbModel> =
        object : PaginatedListAdapter<RepositoryDbModel>(OnItemClickListener { data ->
            val action =
                RepositoriesFragmentDirections.actionRepositoriesFragmentToBranchesFragment(
                    data.workspaceId,
                    data.repositoryId
                )
            findNavController().navigate(action)
        }, { inflater, viewGroup ->
            RepositoryItemBinding.inflate(inflater, viewGroup, false)
        }) {
            override fun getLayoutId(position: Int, obj: RepositoryDbModel): Int =
                R.layout.repository_item
        }

    @ExperimentalPagingApi
    override val viewModel: RepositoriesViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.repositoriesComponent()
            ?.create()?.inject(this)
    }

    @ExperimentalPagingApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        networkConnectivityManager.startConnectionChecking(
            viewLifecycleOwner,
            binding.root,
            binding.textViewRepositoriesNoInternet
        )
    }

    override fun getLayoutResId(): Int = R.layout.fragment_repositories

    override fun getRecyclerView(): RecyclerView {
        binding.recyclerViewRepositoriesReposList.addItemDecoration(itemDecoration)
        return binding.recyclerViewRepositoriesReposList
    }
}
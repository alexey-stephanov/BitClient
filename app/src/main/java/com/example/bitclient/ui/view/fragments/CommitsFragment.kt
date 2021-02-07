package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.database.CommitsDao
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitModel
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.data.repositories.userrepositories.RepositoriesRepository
import com.example.bitclient.databinding.CommitItemBinding
import com.example.bitclient.databinding.FragmentCommitsBinding
import com.example.bitclient.ui.recyclerview.OnItemClickListener
import com.example.bitclient.ui.recyclerview.PaginatedListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.CommitsViewModel
import com.example.bitclient.ui.viewmodels.CommitsViewModelFactory
import javax.inject.Inject

class CommitsFragment : PaginatedFragment<CommitModel, CommitDbModel>() {

    private val binding by viewBinding(FragmentCommitsBinding::bind)

    private val args: CommitsFragmentArgs by navArgs()

    @Inject
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    @Inject
    lateinit var repositoriesRepository: RepositoriesRepository

    @Inject
    lateinit var commitsDao: CommitsDao

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    @ExperimentalPagingApi
    override val viewModel: CommitsViewModel by viewModels {
        CommitsViewModelFactory(
            repositoriesRepository,
            commitsDao,
            args.workspaceId!!,
            args.repositoryId!!,
            args.branchName!!
        )
    }

    override val paginatedListAdapter: PaginatedListAdapter<CommitDbModel> =
        object : PaginatedListAdapter<CommitDbModel>(
            OnItemClickListener { },
            { inflater, parent ->
                CommitItemBinding.inflate(inflater, parent, false)
            }
        ) {
            override fun getLayoutId(position: Int, obj: CommitDbModel): Int =
                R.layout.commit_item
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.repositoriesComponent()
            ?.create()?.inject(this)
    }

    @ExperimentalPagingApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding.toolbarCommitsActionbar) {
            title = args.branchName
            setNavigationIcon(R.drawable.ic_left_arrow)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }

        networkConnectivityManager.startConnectionChecking(
            viewLifecycleOwner,
            binding.root,
            binding.textViewCommitsNoInternet
        )
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_commits

    override fun getRecyclerView(): RecyclerView {
        binding.recyclerViewCommitsCommitsList.addItemDecoration(itemDecoration)
        return binding.recyclerViewCommitsCommitsList
    }
}
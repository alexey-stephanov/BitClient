package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitModel
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.data.repositories.userrepositories.UserRepositoriesRepository
import com.example.bitclient.databinding.CommitItemBinding
import com.example.bitclient.databinding.FragmentCommitsBinding
import com.example.bitclient.ui.recyclerview.OnItemClickListener
import com.example.bitclient.ui.recyclerview.PaginatedListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.CommitsViewModel
import com.example.bitclient.ui.viewmodels.CommitsViewModelFactory
import javax.inject.Inject

class CommitsFragment : PaginatedFragment<CommitModel>() {

    private val binding by viewBinding(FragmentCommitsBinding::bind)

    private val args: CommitsFragmentArgs by navArgs()

    @Inject
    lateinit var networkConnectivityManager: NetworkConnectivityManager

    @Inject
    lateinit var userRepositoriesRepository: UserRepositoriesRepository

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    override val viewModel: CommitsViewModel by viewModels {
        CommitsViewModelFactory(
            userRepositoriesRepository,
            args.workspaceId!!,
            args.repositoryId!!,
            args.branchName!!
        )
    }
    override val paginatedListAdapter: PaginatedListAdapter<CommitModel> =
        object : PaginatedListAdapter<CommitModel>(
            OnItemClickListener { },
            { inflater, parent ->
                CommitItemBinding.inflate(inflater, parent, false)
            }
        ) {
            override fun getLayoutId(position: Int, obj: CommitModel): Int =
                R.layout.commit_item
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.repositoriesComponent()
            ?.create()?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.toolbarCommitsActionbar.title = args.branchName

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
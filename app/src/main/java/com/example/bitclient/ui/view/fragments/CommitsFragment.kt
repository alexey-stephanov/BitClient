package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitModel
import com.example.bitclient.databinding.CommitItemBinding
import com.example.bitclient.databinding.FragmentCommitsBinding
import com.example.bitclient.ui.recyclerview.listeners.OnItemClickListener
import com.example.bitclient.ui.recyclerview.adapters.PaginatedListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.viewmodels.CommitsViewModel
import com.example.bitclient.viewmodels.factories.CommitsViewModelFactory
import com.facebook.shimmer.ShimmerFrameLayout
import javax.inject.Inject

class CommitsFragment : PaginatedFragment<CommitModel, CommitDbModel>() {

    private val binding by viewBinding(FragmentCommitsBinding::bind)

    private val args: CommitsFragmentArgs by navArgs()

    @Inject
    lateinit var commitsViewModelFactory: CommitsViewModelFactory

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    @ExperimentalPagingApi
    override val viewModel: CommitsViewModel by lazy {
        commitsViewModelFactory.create(args.workspaceId!!, args.repositoryId!!, args.branchName!!)
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

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.repositoriesSubcomponentManager()
            ?.repositoriesSubcomponent?.branchesSubcomponentManager()?.branchesSubcomponent?.commitsSubcomponent()?.create()?.inject(this)
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        networkConnectivityManager.startConnectionChecking(
//            viewLifecycleOwner,
//            binding.root,
//            binding.textViewCommitsNoInternet
//        )
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_commits

    override fun bindShimmerFrameLayout(): ShimmerFrameLayout =
        binding.containerCommitsPlaceholder

    override fun bindRecyclerView(): RecyclerView {
        binding.recyclerViewCommitsCommitsList.addItemDecoration(itemDecoration)
        return binding.recyclerViewCommitsCommitsList
    }
}
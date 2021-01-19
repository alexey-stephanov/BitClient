package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.databinding.FragmentRepositoriesBinding
import com.example.bitclient.ui.recyclerview.PaginatedListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.RepositoriesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesFragment : PaginatedFragment<RepositoryModel, RepositoriesViewModel>() {

    private val binding by viewBinding(FragmentRepositoriesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    override val paginatedListAdapter: PaginatedListAdapter<RepositoryModel> =
        object : PaginatedListAdapter<RepositoryModel>() {
            override fun getLayoutId(position: Int, obj: RepositoryModel): Int =
                R.layout.repository_item
        }

    override val viewModel: RepositoriesViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.repositoriesComponent()
            ?.create()?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startConnectionChecking()
    }

    override fun getLayoutResId(): Int = R.layout.fragment_repositories

    override fun getRecyclerView(): RecyclerView {
        val recyclerView = binding.recyclerViewRepositoriesReposList
        recyclerView.addItemDecoration(itemDecoration)
        return recyclerView
    }

    private fun startConnectionChecking() {
        NetworkStatus.observe(viewLifecycleOwner, { isAvailable ->
            TransitionManager.beginDelayedTransition(binding.root, Slide())
            binding.textViewRepositoriesNoInternet.isVisible = !isAvailable
        })
    }
}
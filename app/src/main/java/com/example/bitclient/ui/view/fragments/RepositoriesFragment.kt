package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.networkavailability.NetworkLiveData
import com.example.bitclient.databinding.FragmentRepositoriesBinding
import com.example.bitclient.ui.adapters.RepositoriesListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.RepositoriesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {

    private val binding by viewBinding(FragmentRepositoriesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var repositoriesViewModel: RepositoriesViewModel

    @Inject
    lateinit var repositoriesListAdapter: RepositoriesListAdapter
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userComponentManager().userComponent!!.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repositoriesViewModel = ViewModelProvider(this, viewModelFactory).get(RepositoriesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewRepositoriesReposList.apply {
            layoutManager = linearLayoutManager
            addItemDecoration(itemDecoration)
            setHasFixedSize(true)
            adapter = repositoriesListAdapter
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repositoriesViewModel.repositories.collectLatest {
                repositoriesListAdapter.submitData(it)
            }
        }
        binding.progressBarRepositoriesLoading.isGone = true
        startConnectionChecking()
    }

    private fun startConnectionChecking() {
        NetworkLiveData.observe(viewLifecycleOwner, { isAvailable ->
            TransitionManager.beginDelayedTransition(binding.root, Slide())
            binding.textViewRepositoriesNoInternet.isVisible = !isAvailable
        })
    }
}
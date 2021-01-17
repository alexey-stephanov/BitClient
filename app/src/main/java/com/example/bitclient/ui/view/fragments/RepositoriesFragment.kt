package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import com.example.bitclient.databinding.FragmentRepositoriesBinding
import com.example.bitclient.ui.recyclerview.RepositoriesListAdapter
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.RepositoriesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {

    private val binding by viewBinding(FragmentRepositoriesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val repositoriesViewModel: RepositoriesViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    @Inject
    lateinit var repositoriesListAdapter: RepositoriesListAdapter

    private val adapter by lazy {
        ArrayAdapter<String>(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.repositoriesComponent()
            ?.create()?.inject(this)
    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startConnectionChecking()
        setupView()
        subscribeOnUserRepositories()
    }

    private fun setupView() {
        setupSpinner()
        setupRecyclerView()
    }

    private fun setupSpinner() {
        repositoriesViewModel.liveWorkspaces.observe(viewLifecycleOwner, { workspaces ->
            val workspacesNames: List<String> = workspaces.map { it.workspaceName }
            adapter.addAll(workspacesNames)
            binding.spinnerRepositoriesWorkspaces.adapter = adapter
            binding.spinnerRepositoriesWorkspaces.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        repositoriesViewModel.switchWorkspaces(position)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerViewRepositoriesReposList.apply {
            layoutManager = linearLayoutManager
            addItemDecoration(itemDecoration)
            setHasFixedSize(true)
            adapter = repositoriesListAdapter
        }
    }

    @ExperimentalCoroutinesApi
    private fun subscribeOnUserRepositories() {
        viewLifecycleOwner.lifecycleScope.launch {
            repositoriesViewModel.repositoriesFlow.collectLatest { pagingData ->
                repositoriesListAdapter.submitData(pagingData)
            }
        }
    }

    private fun startConnectionChecking() {
        NetworkStatus.observe(viewLifecycleOwner, { isAvailable ->
            TransitionManager.beginDelayedTransition(binding.root, Slide())
            binding.textViewRepositoriesNoInternet.isVisible = !isAvailable
        })
    }
}
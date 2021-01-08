package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.TokenManager
import com.example.bitclient.data.network.TokenStatus
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspaceModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse
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
    private val repositoriesViewModel: RepositoriesViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var tokenManager: TokenManager

    @Inject
    lateinit var repositoriesListAdapter: RepositoriesListAdapter
    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userComponentManager().userComponent?.repositoriesComponent()?.create()?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startConnectionChecking()
        observeTokenStatus()
    }

    private fun observeTokenStatus() {
        val userTokensObserver = Observer<TokenStatus> { tokensStatus ->
            when(tokensStatus) {
                is TokenStatus.Loading -> {
                    Log.e("TOKEN_LOADING", "Pls wait.")
                }
                is TokenStatus.Ready -> {
                    setupRecyclerView()
                    loadUserWorkspacesIntoUI()
                }
                is TokenStatus.Error -> {
                    Log.e("TOKEN_ERROR", "Some problems with token.")
                }
            }
        }
        tokenManager.tokenStatusLiveData.observe(viewLifecycleOwner, userTokensObserver)
    }

    private fun loadUserWorkspacesIntoUI() {
        binding.progressBarRepositoriesLoading.isVisible = true
        repositoriesViewModel.loadUserWorkspaces()
        repositoriesViewModel.liveWorkspacesModel.observe(viewLifecycleOwner, { userWorkspaces ->
            setupSpinner(userWorkspaces)
        })
    }

    private fun setupSpinner(userWorkspaces: WorkspacesResponse) {
        val workspacesNamesList = mutableListOf<String>()
        userWorkspaces.workspaces.forEach { workspaces ->
            workspacesNamesList.add(workspaces.workspaceName)
        }
        val adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, workspacesNamesList)
        binding.spinnerRepositoriesWorkspaces.adapter = adapter
        binding.spinnerRepositoriesWorkspaces.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                loadUserRepositories(userWorkspaces, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewRepositoriesReposList.apply {
            layoutManager = object : LinearLayoutManager(requireContext(), VERTICAL, false) {
                override fun onLayoutCompleted(state: RecyclerView.State?) {
                    super.onLayoutCompleted(state)
                    binding.progressBarRepositoriesLoading.isGone = true
                }
            }
            addItemDecoration(itemDecoration)
            setHasFixedSize(true)
            adapter = repositoriesListAdapter
        }

    }

    private fun loadUserRepositories(userWorkspaces: WorkspacesResponse, position: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            repositoriesViewModel.getRepositoriesFlow(userWorkspaces.workspaces[position].workspaceId).collectLatest { pagingData ->
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
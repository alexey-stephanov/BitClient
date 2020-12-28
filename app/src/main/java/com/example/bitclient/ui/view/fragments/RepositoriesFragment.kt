package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.NetworkLiveData
import com.example.bitclient.databinding.FragmentRepositoriesBinding
import com.example.bitclient.di.UserComponentManager
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.RepositoriesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {

    private val binding by viewBinding(FragmentRepositoriesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var repositoriesViewModel: RepositoriesViewModel

    private lateinit var userComponentManager: UserComponentManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        userComponentManager = (requireActivity().application as BitClientApp).appComponent.userComponentManager()
        userComponentManager.createComponent()
        userComponentManager.userComponent!!.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repositoriesViewModel = ViewModelProvider(this, viewModelFactory).get(RepositoriesViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        startConnectionChecking()
    }

    private fun startConnectionChecking() {
        NetworkLiveData.observe(this, {
            if (it) {
                if (binding.textViewRepositoriesNoInternet.isVisible)
                    binding.textViewRepositoriesNoInternet.visibility = View.GONE
            } else {
                binding.textViewRepositoriesNoInternet.visibility = View.VISIBLE
            }
        })
    }
}
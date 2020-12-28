package com.example.bitclient.ui.view.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.databinding.FragmentBranchesBinding
import com.example.bitclient.di.UserComponentManager
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.BranchesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class BranchesFragment : Fragment(R.layout.fragment_branches) {

    private val binding by viewBinding(FragmentBranchesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var branchesViewModel: BranchesViewModel

    private lateinit var userComponentManager: UserComponentManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        userComponentManager = (requireActivity().application as BitClientApp).appComponent.userComponentManager()
        userComponentManager.userComponent!!.inject(this)
    }
}
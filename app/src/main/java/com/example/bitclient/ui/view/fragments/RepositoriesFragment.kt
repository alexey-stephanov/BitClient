package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.databinding.FragmentRepositoriesBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.RepositoriesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {

    private val binding by viewBinding(FragmentRepositoriesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var repositoriesViewModel: RepositoriesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val userManager = (requireActivity().application as BitClientApp).appComponent.userManager()
        userManager.loginUser()
        userManager.userComponent!!.inject(this)
    }
}
package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsCallback
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.databinding.FragmentAuthorizationBinding
import com.example.bitclient.di.AuthorizationComponent
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.AuthorizationViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import retrofit2.Retrofit
import javax.inject.Inject

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private val binding by viewBinding(FragmentAuthorizationBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var authorizationViewModel: AuthorizationViewModel

    lateinit var authorizationComponent: AuthorizationComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)

        authorizationComponent = (requireActivity().application as BitClientApp).appComponent.authorizationComponent().create()
        authorizationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authorizationViewModel = ViewModelProvider(this, viewModelFactory).get(AuthorizationViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAuthorizationEnter.setOnClickListener {

            val url = authorizationViewModel.url
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(url))

            //view.findNavController().navigate(AuthorizationFragmentDirections.actionSignInFragmentToRepositoriesFragment())
            //Toast.makeText(requireContext(), "FFFFFFFFFF", Toast.LENGTH_LONG).show()
        }
    }
}
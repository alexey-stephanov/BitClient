package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.databinding.FragmentAuthorizationBinding
import com.example.bitclient.di.AuthorizationComponent
import com.example.bitclient.ui.view.activities.MainActivity
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.AuthorizationViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private val fragmentSignInBinding by viewBinding(FragmentAuthorizationBinding::bind)
    private val clientId = "cDXfEkaZFs8aSvnVgT"

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var authorizationViewModel: AuthorizationViewModel

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

        authorizationViewModel.sayA()

        fragmentSignInBinding.buttonAuthorizationEnter.setOnClickListener {
            val url = "https://bitbucket.org/site/oauth2/authorize?client_id=$clientId"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
        }
    }
}
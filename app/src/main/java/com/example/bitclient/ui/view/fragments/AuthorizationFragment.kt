package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.bitclient.BitClientApp
import com.example.bitclient.BuildConfig
import com.example.bitclient.R
import com.example.bitclient.databinding.FragmentAuthorizationBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.viewmodels.AuthorizationViewModel
import com.example.bitclient.viewmodels.factories.ViewModelFactory
import javax.inject.Inject

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private val binding by viewBinding(FragmentAuthorizationBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val authorizationViewModel: AuthorizationViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.authorizationSubcomponent()
            .create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeResponseStatus(view)

        binding.buttonAuthorizationEnter.setOnClickListener {
            val url =
                "${BuildConfig.AUTH_URL}site/oauth2/authorize?client_id=${BuildConfig.CLIENT_ID}&response_type=code"
            val customTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
        }
    }

    private fun observeResponseStatus(view: View) {
        authorizationViewModel.resultLiveData.observe(
            viewLifecycleOwner,
            { result ->
                if(result) {
                val action = AuthorizationFragmentDirections.actionAuthorizationFragmentToBottomNavigationFragment()
                view.findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
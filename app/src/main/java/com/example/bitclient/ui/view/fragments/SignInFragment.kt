package com.example.bitclient.ui.view.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.example.bitclient.R
import com.example.bitclient.databinding.FragmentSignInBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val fragmentSignInBinding by viewBinding(FragmentSignInBinding::bind)
    private val clientId = "cDXfEkaZFs8aSvnVgT"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSignInBinding.buttonSignInEnter.setOnClickListener {
            val url = "https://bitbucket.org/site/oauth2/authorize?client_id=$clientId"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
        }
    }
}
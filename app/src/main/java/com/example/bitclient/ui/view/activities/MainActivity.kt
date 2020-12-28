package com.example.bitclient.ui.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.databinding.ActivityMainBinding
import com.example.bitclient.ui.view.fragments.AuthorizationFragmentDirections
import com.example.bitclient.ui.viewmodels.MainViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as BitClientApp).appComponent.inject(this)

        setTheme(R.style.Theme_BitClient)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val code = intent?.data?.getQueryParameter("code")
        if (code != null) {
            mainViewModel.handleAuthorizationCode(code)
            Navigation.findNavController(this, R.id.main_host_fragment)
                .navigate(AuthorizationFragmentDirections.actionAuthorizationFragmentToBottomNavigationFragment())
        }
    }
}
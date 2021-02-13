package com.example.bitclient.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.storage.Storage
import com.example.bitclient.databinding.ActivityMainBinding
import com.example.bitclient.ui.viewmodels.MainViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var storage: Storage

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as BitClientApp).appComponent.inject(this)

        setTheme(R.style.Theme_BitClient)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFragment()
    }

    private fun openFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation_graph)
        if (storage.getString("access_token") != "") {
            graph.startDestination = R.id.bottomNavigationFragment
        } else {
            graph.startDestination = R.id.authorizationFragment
        }
        navHostFragment.navController.graph = graph
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val code = intent?.data?.getQueryParameter("code")
        if (code != null) {
            mainViewModel.handleAuthorizationCode(code)
        }
    }
}
package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.databinding.FragmentRepositoriesBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.ui.viewmodels.RepositoriesViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject

class RepositoriesFragment : Fragment(R.layout.fragment_repositories), NavigationView.OnNavigationItemSelectedListener {

    private val binding by viewBinding(FragmentRepositoriesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var repositoriesViewModel: RepositoriesViewModel

    private lateinit var userManager: UserManager

    private lateinit var drawer: DrawerLayout

    override fun onAttach(context: Context) {
        super.onAttach(context)

        userManager = (requireActivity().application as BitClientApp).appComponent.userManager()
        userManager.createComponent()
        userManager.userComponent!!.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.setTheme(R.style.Theme_BitClient)
        repositoriesViewModel = ViewModelProvider(this, viewModelFactory).get(RepositoriesViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupDrawer()
        binding.navViewRepositoriesNavigation.setNavigationItemSelectedListener(this)
    }

    private fun setupDrawer() {
        drawer = binding.drawerLayoutRepositoriesLayout
        binding.imageButtonRepositoriesMenu.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
        //fillDrawerWithData()
    }

    private fun fillDrawerWithData() {
        binding.textViewRepositoriesTitle.text = repositoriesViewModel.getUserInfo()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_settings -> view?.findNavController()?.navigate(RepositoriesFragmentDirections.actionRepositoriesFragmentToSettingsFragment())
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
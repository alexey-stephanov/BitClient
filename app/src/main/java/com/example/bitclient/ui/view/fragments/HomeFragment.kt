package com.example.bitclient.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.databinding.FragmentHomeBinding
import com.example.bitclient.ui.view.fragments.viewbinding.viewBinding
import com.example.bitclient.viewmodels.HomeViewModel
import com.example.bitclient.viewmodels.factories.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BitClientApp).appComponent.userSubcomponentManager().userSubcomponent?.inject(
            this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAccountData()

    }

    private fun observeAccountData() {
        homeViewModel.accountData.observe(viewLifecycleOwner, { accountData ->
            setupView(accountData)
        })
    }

    private fun setupView(accountData: AccountDbModel) {
//        val adapter = BaseListAdapter<HomeButtonModel>(, { inflater, viewGroup ->
//            HomeButtonItemBinding.inflate(inflater, viewGroup, false)
//        }) {
//            override fun bindItem(item: HomeButtonModel, binding: ViewBinding) {
//                with(binding as HomeButtonItemBinding) {
//                    textViewHomeButtonTitle.text = item.title
//                    imageViewHomeButtonIcon.setImageResource(item.iconRef)
//                    imageViewHomeButtonIcon.setBackgroundResource(item.iconBg)
//                    layoutHomeButtonItem.setOnClickListener { item.clickListener() }
//                }
//            }
//        }.apply {
//            submitList(listOf(HomeButtonModel(getString(R.string.repositories_title), R.drawable.ic_repositories, R.color.red_700) {
//                val action =
//                    HomeFragmentDirections.actionHomeFragmentToRepositoriesFragment(accountData.workspaceId)
//                findNavController().navigate(action)
//            }, HomeButtonModel(getString(R.string.organizations_title), R.drawable.ic_organizations, R.color.blue_800) {
//                val action =
//                    HomeFragmentDirections.actionHomeFragmentToOrganizationsFragment(accountData.accountId)
//                findNavController().navigate(action)
//            }, HomeButtonModel(getString(R.string.pull_requests_title), R.drawable.ic_pull_requests, R.color.peach_800) {}))
//        }
//        binding.recyclerViewHomeButtonsList.adapter = adapter
    }
}
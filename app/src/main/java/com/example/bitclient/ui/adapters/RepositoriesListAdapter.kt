package com.example.bitclient.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.R
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.databinding.RepositoryItemBinding
import com.facebook.drawee.view.SimpleDraweeView
import javax.inject.Inject

class RepositoriesListAdapter : PagingDataAdapter<RepositoryModel, RepositoriesListAdapter.RepositoriesViewHolder>(REPOSITORY_COMPARATOR) {

    private lateinit var binding: RepositoryItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RepositoryItemBinding.inflate(inflater)
        return RepositoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class RepositoriesViewHolder(private val binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryModel) {
            with(binding) {
                imageViewRepositoryItemIcon.setImageURI(repository.links.avatar.href)
                textViewRepositoryItemRepoName.text = repository.name
                textViewRepositoryItemFullName.text = repository.fullName
                if (repository.isPrivate == "true") {
                    imageViewRepositoryItemLockImage.isVisible = true
                    imageViewRepositoryItemLockImage.setOnClickListener {
                        Toast.makeText(
                            binding.root.context,
                            "Repository is private",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
    }

    companion object {
        private val REPOSITORY_COMPARATOR = object : DiffUtil.ItemCallback<RepositoryModel>() {
            override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean =
                oldItem.repositoryId == newItem.repositoryId

            override fun areContentsTheSame(
                oldItem: RepositoryModel,
                newItem: RepositoryModel
            ): Boolean =
                oldItem == newItem
        }
    }
}
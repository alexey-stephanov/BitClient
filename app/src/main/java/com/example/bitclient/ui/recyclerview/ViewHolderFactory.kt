package com.example.bitclient.ui.recyclerview

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.databinding.RepositoryItemBinding

object ViewHolderFactory {
    fun create(binding: ViewBinding, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.repository_item -> RepositoryViewHolder(binding)
            else -> throw Exception("Wrong type.")
        }
    }

    class RepositoryViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root), PaginatedListAdapter.Binder<RepositoryModel> {
        override fun bind(data: RepositoryModel) {
            with(binding as RepositoryItemBinding) {
                imageViewRepositoryItemIcon.setImageURI(data.links.avatar.href)
                textViewRepositoryItemRepoName.text = data.name
                textViewRepositoryItemFullName.text = data.fullName
                if (data.isPrivate) {
                    imageViewRepositoryItemLockImage.isVisible = true
                }
            }
        }
    }
}
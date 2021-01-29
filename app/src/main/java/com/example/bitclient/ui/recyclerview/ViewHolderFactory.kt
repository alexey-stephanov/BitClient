package com.example.bitclient.ui.recyclerview

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchModel
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.databinding.BranchItemBinding
import com.example.bitclient.databinding.CommitItemBinding
import com.example.bitclient.databinding.RepositoryItemBinding

object ViewHolderFactory {
    fun create(binding: ViewBinding, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.repository_item -> RepositoryViewHolder(binding as RepositoryItemBinding)
            R.layout.branch_item -> BranchViewHolder(binding as BranchItemBinding)
            R.layout.commit_item -> CommitViewHolder(binding as CommitItemBinding)
            else -> throw Exception("Wrong type.")
        }
    }

    class RepositoryViewHolder(private val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root), PaginatedListAdapter.Binder<RepositoryModel> {
        override fun bind(data: RepositoryModel, listener: OnItemClickListener<RepositoryModel>?) {
            with(binding) {
                imageViewRepositoryItemIcon.setImageURI(data.links.avatar.href)
                textViewRepositoryItemRepoName.text = data.name
                textViewRepositoryItemFullName.text = data.fullName
                if (data.isPrivate) {
                    imageViewRepositoryItemLockImage.isVisible = true
                }
                constraintLayoutRepositoryItemLayout.setOnClickListener { listener?.onItemClick(data) }
            }
        }
    }

    class BranchViewHolder(private val binding: BranchItemBinding) : RecyclerView.ViewHolder(binding.root), PaginatedListAdapter.Binder<BranchModel> {
        override fun bind(data: BranchModel, listener: OnItemClickListener<BranchModel>?) {
            with(binding) {
                textViewBranchItemBranchName.text = data.branchName
                frameLayoutBranchItemLayout.setOnClickListener { listener?.onItemClick(data) }
            }
        }
    }

    class CommitViewHolder(private val binding: CommitItemBinding) : RecyclerView.ViewHolder(binding.root), PaginatedListAdapter.Binder<CommitModel> {
        override fun bind(data: CommitModel, listener: OnItemClickListener<CommitModel>?) {
            with(binding) {
                textViewCommitItemCommitName.text = "name"
                textViewCommitItemCommitMessage.text = data.massage
                textViewCommitItemCommitDate.text = data.date
                textViewCommitItemCommitAuthor.text = data.authorModel.author
            }
        }
    }
}
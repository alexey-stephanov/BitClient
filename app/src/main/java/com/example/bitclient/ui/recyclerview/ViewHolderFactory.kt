package com.example.bitclient.ui.recyclerview

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bitclient.R
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel
import com.example.bitclient.databinding.BranchItemBinding
import com.example.bitclient.databinding.CommitItemBinding
import com.example.bitclient.databinding.OrganizationItemBinding
import com.example.bitclient.databinding.RepositoryItemBinding

object ViewHolderFactory {
    fun create(binding: ViewBinding, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.repository_item -> RepositoryViewHolder(binding as RepositoryItemBinding)
            R.layout.branch_item -> BranchViewHolder(binding as BranchItemBinding)
            R.layout.commit_item -> CommitViewHolder(binding as CommitItemBinding)
            R.layout.organization_item -> OrganizationViewHolder(binding as OrganizationItemBinding)
            else -> throw Exception("Wrong type.")
        }
    }

    class RepositoryViewHolder(private val binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root), PaginatedListAdapter.Binder<RepositoryDbModel> {
        override fun bind(
            data: RepositoryDbModel,
            listener: OnItemClickListener<RepositoryDbModel>?
        ) {
            with(binding) {
                imageViewRepositoryItemIcon.setImageURI(data.avatarLink)
                textViewRepositoryItemRepoName.text = data.name
                textViewRepositoryItemFullName.text = data.fullName
                if (data.isPrivate) {
                    imageViewRepositoryItemLockImage.isVisible = true
                }
                constraintLayoutRepositoryItemLayout.setOnClickListener { listener?.onItemClick(data) }
            }
        }
    }

    class BranchViewHolder(private val binding: BranchItemBinding) :
        RecyclerView.ViewHolder(binding.root), PaginatedListAdapter.Binder<BranchDbModel> {
        override fun bind(data: BranchDbModel, listener: OnItemClickListener<BranchDbModel>?) {
            with(binding) {
                textViewBranchItemBranchName.text = data.branchName
                frameLayoutBranchItemLayout.setOnClickListener { listener?.onItemClick(data) }
            }
        }
    }

    class CommitViewHolder(private val binding: CommitItemBinding) :
        RecyclerView.ViewHolder(binding.root), PaginatedListAdapter.Binder<CommitDbModel> {
        override fun bind(data: CommitDbModel, listener: OnItemClickListener<CommitDbModel>?) {
            val str = data.message.split("\n").filter { s -> s.isNotEmpty() }
            with(binding) {
                textViewCommitItemCommitName.text = str[0]
                if (str.size == 2) textViewCommitItemCommitMessage.text = str[1] else textViewCommitItemCommitMessage.visibility = View.GONE
                textViewCommitItemCommitDate.text = data.date
                textViewCommitItemCommitAuthor.text = data.authorName
            }
        }
    }

    class OrganizationViewHolder(private val binding: OrganizationItemBinding) : RecyclerView.ViewHolder(binding.root), PaginatedListAdapter.Binder<WorkspaceDbModel> {
        override fun bind(
            data: WorkspaceDbModel,
            listener: OnItemClickListener<WorkspaceDbModel>?
        ) {
            with(binding) {
                textViewOrganizationItemWorkspaceName.text = data.workspaceName
                frameLayoutOrganizationItemLayout.setOnClickListener { listener?.onItemClick(data) }
            }
        }
    }
}
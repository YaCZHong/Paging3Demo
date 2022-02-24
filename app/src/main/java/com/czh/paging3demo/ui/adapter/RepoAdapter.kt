package com.czh.paging3demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.czh.paging3demo.R
import com.czh.paging3demo.http.model.Repo

class RepoAdapter : PagingDataAdapter<Repo, RepoAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRepoName = itemView.findViewById<AppCompatTextView>(R.id.tv_repo_name)
        private val tvRepoDesc = itemView.findViewById<AppCompatTextView>(R.id.tv_repo_desc)
        private val tvRepoStarts = itemView.findViewById<AppCompatTextView>(R.id.tv_repo_starts)
        private var current: Repo? = null

        init {
            itemView.setOnClickListener {
                current?.let { repo ->
                    updateStar(repo, absoluteAdapterPosition)
                }
            }
        }

        fun bind(repo: Repo) {
            this.current = repo
            tvRepoName.text = repo.name
            tvRepoDesc.text = repo.description
            tvRepoStarts.text = repo.starCount.toString()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        )
    }

    private fun updateStar(repo: Repo, position: Int) {
        repo.starCount = repo.starCount + 1
        notifyItemChanged(position)
    }
}
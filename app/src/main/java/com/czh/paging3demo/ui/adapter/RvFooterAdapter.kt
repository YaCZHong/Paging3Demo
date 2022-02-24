package com.czh.paging3demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czh.paging3demo.R

class RvFooterAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<RvFooterAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val btnRetry = itemView.findViewById<AppCompatButton>(R.id.btn_retry)
        private val pb = itemView.findViewById<ProgressBar>(R.id.pb)

        init {
            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            btnRetry.isVisible = loadState is LoadState.Error
            pb.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_footer, parent, false)
        )
    }
}
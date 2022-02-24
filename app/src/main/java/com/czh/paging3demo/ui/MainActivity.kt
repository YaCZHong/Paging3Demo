package com.czh.paging3demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.czh.paging3demo.R
import com.czh.paging3demo.ui.adapter.RepoAdapter
import com.czh.paging3demo.ui.adapter.RvFooterAdapter
import com.czh.paging3demo.ui.decoration.MyDecoration
import com.czh.paging3demo.vm.MainVM
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var btnRetry: AppCompatButton
    private lateinit var pb: ProgressBar
    private val vm by lazy { ViewModelProvider(this).get(MainVM::class.java) }
    private val mAdapter = RepoAdapter()
    private val mFooterAdapter = RvFooterAdapter {
        mAdapter.retry()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        pb = findViewById(R.id.pb)
        btnRetry = findViewById(R.id.btn_retry)
        rv = findViewById(R.id.rv)
        mAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    pb.isVisible = false
                    btnRetry.isVisible = false
                }
                is LoadState.Loading -> {
                    pb.isVisible = true
                    btnRetry.isVisible = false
                }
                is LoadState.Error -> {
                    pb.isVisible = false
                    btnRetry.isVisible = true
                    Toast.makeText(this, R.string.loadErrorHint, Toast.LENGTH_SHORT).show()
                }
            }
        }
        rv.adapter = mAdapter.withLoadStateFooter(mFooterAdapter)
        rv.addItemDecoration(MyDecoration())
        btnRetry.setOnClickListener {
            mAdapter.retry()
        }
    }

    private fun initData() {
        lifecycleScope.launch {
            vm.loadRepositories().collect { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }
}
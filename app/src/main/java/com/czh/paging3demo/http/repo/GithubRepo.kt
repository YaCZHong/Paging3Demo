package com.czh.paging3demo.http.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.czh.paging3demo.http.HttpManager
import com.czh.paging3demo.http.api.GithubApi
import com.czh.paging3demo.http.model.Repo
import com.czh.paging3demo.paging.GithubPagingSource
import kotlinx.coroutines.flow.Flow

object GithubRepo {
    private const val PAGE_SIZE = 50
    private val api = HttpManager.retrofit.create(GithubApi::class.java)

    fun loadRepositories(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { GithubPagingSource(api) }).flow
    }
}
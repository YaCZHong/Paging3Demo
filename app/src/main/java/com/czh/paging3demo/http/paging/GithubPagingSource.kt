package com.czh.paging3demo.http.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.czh.paging3demo.http.api.GithubApi
import com.czh.paging3demo.http.model.Repo

class GithubPagingSource(private val githubApi: GithubApi) : PagingSource<Int, Repo>() {

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val pageIndex = params.key ?: 1
            val pageSize = params.loadSize
            val result = githubApi.searchRepositories("Android", "stars", pageIndex, pageSize)
            val prev = if (pageIndex > 1) pageIndex - 1 else null
            val next = if (result.items.isNotEmpty()) pageIndex + 1 else null
            LoadResult.Page(result.items, prev, next)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
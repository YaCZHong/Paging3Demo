package com.czh.paging3demo.http.api

import com.czh.paging3demo.http.SEARCH_REPOSITORIES
import com.czh.paging3demo.http.model.SearchRepositoriesResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET(SEARCH_REPOSITORIES)
    suspend fun searchRepositories(
        @Query("q") queryType: String,
        @Query("sort") sortType: String,
        @Query("page") pageIndex: Int,
        @Query("per_page") pageSize: Int
    ): SearchRepositoriesResult
}
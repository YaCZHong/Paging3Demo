package com.czh.paging3demo.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.czh.paging3demo.http.model.Repo
import com.czh.paging3demo.http.repo.GithubRepo
import kotlinx.coroutines.flow.Flow

class MainVM : ViewModel() {

    fun loadRepositories(): Flow<PagingData<Repo>> {
        return GithubRepo.loadRepositories().cachedIn(viewModelScope)
    }
}
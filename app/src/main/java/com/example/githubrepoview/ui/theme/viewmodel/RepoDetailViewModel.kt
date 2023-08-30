package com.example.githubrepoview.ui.theme.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoview.ui.theme.state.RepoDetailState
import com.example.githubrepoview.domain.model.RepoDetail
import com.example.githubrepoview.ui.theme.models.UiRepoDetail
import com.example.githubrepoview.usecase.GetRepoDetailDatabaseUseCase
import com.example.githubrepoview.usecase.GetRepoDetailUseCase
import com.example.githubrepoview.utils.DataResult
import com.example.githubrepoview.utils.Mapper.mapEntityToRepoDetailUiRepoDetail
import com.example.githubrepoview.utils.Mapper.mapRepoDetailToUiRepoDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(
    private val getRepoDetailUseCase: GetRepoDetailUseCase,
    private val getRepositoriesDatabaseDetailUseCase: GetRepoDetailDatabaseUseCase
) :
    ViewModel() {
    private val _repoDetailState = mutableStateOf(RepoDetailState())
    val repoDetailState: State<RepoDetailState> = _repoDetailState

    fun loadRepoDetailFromApi(owner: String, repo: String, nodeId: String) {
        getRepoDetailUseCase(owner, repo).onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    Log.d("testtt", "loadRepoDetailFromApi:1 ${result.data}")
                    _repoDetailState.value = RepoDetailState(repoList = result.data?.let {
                        mapRepoDetailToUiRepoDetail(
                            it
                        )
                    })
                }

                is DataResult.Loading -> {
                    Log.d("testtt", "loadRepoDetailFromAp:2")
                    _repoDetailState.value = RepoDetailState(isLoading = true)
                }

                is DataResult.Error -> {
                    Log.d("testtt", "loadRepoDetailFromAp:3 ${result.message}")
                    _repoDetailState.value =
                        RepoDetailState(error = result.message ?: "unexpected error")
                    loadRepoDetailFromDataBase(nodeId)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun loadRepoDetailFromDataBase(nodeId: String) {
        getRepositoriesDatabaseDetailUseCase(nodeId).onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _repoDetailState.value = RepoDetailState(repoList = result.data?.let {
                        mapEntityToRepoDetailUiRepoDetail(
                            it
                        )
                    })
                }

                is DataResult.Loading -> {
                    _repoDetailState.value = RepoDetailState(isLoading = true)
                }

                is DataResult.Error -> {
                    _repoDetailState.value =
                        RepoDetailState(error = result.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }
}
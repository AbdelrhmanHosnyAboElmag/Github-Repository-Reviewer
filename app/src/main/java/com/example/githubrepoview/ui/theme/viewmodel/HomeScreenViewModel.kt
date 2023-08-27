package com.example.githubrepoview.ui.theme.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoview.ui.theme.models.UiRepositories
import com.example.githubrepoview.ui.theme.state.RepoListState
import com.example.githubrepoview.usecase.GetRepositoriesDatabaseUseCase
import com.example.githubrepoview.usecase.GetRepositoriesUseCase
import com.example.githubrepoview.usecase.GetRepostoriesBySearchUseCase
import com.example.githubrepoview.utils.DataResult
import com.example.githubrepoview.utils.Mapper.mapRepositoriesItemDatabaseToUiRepositories
import com.example.githubrepoview.utils.Mapper.mapRepositoriesItemToUiRepositories
import com.example.githubrepoview.utils.Mapper.mapRepositoriesItemToUiRepositoriesSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val getRepositoriesDatabaseUseCase: GetRepositoriesDatabaseUseCase,
    private val getRepostoriesBySearchUseCase: GetRepostoriesBySearchUseCase
) :
    ViewModel() {
    private val _repoState = mutableStateOf(RepoListState())
    val repoState: State<RepoListState> = _repoState


    init {
        loadRepositoriesFromApi()
    }



    fun loadRepositoriesFromApi() {
        getRepositoriesUseCase().onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _repoState.value = RepoListState(repoList = result.data?.let {
                        mapRepositoriesItemToUiRepositories(
                            it
                        )
                    })
                }

                is DataResult.Loading -> {
                    _repoState.value = RepoListState(isLoading = true)
                }

                is DataResult.Error -> {
                    _repoState.value = RepoListState(error = result.message ?: "unexpected error")
                    loadRepositoriesFromDataBase()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadRepositoriesBySearchFromApi(query:String) {
        getRepostoriesBySearchUseCase(query).onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _repoState.value = RepoListState(repoList = result.data?.let {
                        mapRepositoriesItemToUiRepositoriesSearch(
                            it.items
                        )
                    })
                }

                is DataResult.Loading -> {
                    _repoState.value = RepoListState(isLoading = true)
                }

                is DataResult.Error -> {
                    _repoState.value = RepoListState(error = result.message ?: "unexpected error")
                    Log.d("tesadsa", "loadRepositoriesBySearchFromApi:${result.message} ")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadRepositoriesFromDataBase() {
        getRepositoriesDatabaseUseCase().onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _repoState.value = RepoListState(repoList = result.data?.let {
                        mapRepositoriesItemDatabaseToUiRepositories(
                            it
                        )
                    })
                }

                is DataResult.Loading -> {
                    _repoState.value = RepoListState(isLoading = true)
                }

                is DataResult.Error -> {
                    _repoState.value = RepoListState(error = result.message ?: "unexpected error")

                }
            }
        }.launchIn(viewModelScope)
    }
}
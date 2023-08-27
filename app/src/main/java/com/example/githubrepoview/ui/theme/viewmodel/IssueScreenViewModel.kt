package com.example.githubrepoview.ui.theme.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoview.ui.theme.state.IssueState
import com.example.githubrepoview.usecase.GetIssueUseCase
import com.example.githubrepoview.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class IssueScreenViewModel@Inject constructor(private val getIssueUseCase: GetIssueUseCase) :
    ViewModel() {
    private val _repoIssueState = mutableStateOf(IssueState())
    val repoIssueState: State<IssueState> = _repoIssueState

    fun loadRepoIssueFromApi(owner:String,repo:String) {
        getIssueUseCase(owner,repo).onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _repoIssueState.value = IssueState(repoList = result.data?: mutableListOf())
                }

                is DataResult.Loading -> {
                    _repoIssueState.value = IssueState(isLoading = true)
                }

                is DataResult.Error -> {
                    _repoIssueState.value = IssueState(error = result.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.example.githubrepoview.ui.theme.state

import com.example.githubrepoview.ui.theme.models.UiRepoDetail

data class RepoDetailState (
    val isLoading:Boolean =false,
    val repoList: UiRepoDetail? = UiRepoDetail(),
    val error:String =  ""
)
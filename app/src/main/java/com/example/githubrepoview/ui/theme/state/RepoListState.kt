package com.example.githubrepoview.ui.theme.state

import com.example.githubrepoview.ui.theme.models.UiRepositories

data class RepoListState (
    val isLoading:Boolean =false,
    val repoList:Array<UiRepositories>? = arrayOf(),
    val error:String =  ""
)
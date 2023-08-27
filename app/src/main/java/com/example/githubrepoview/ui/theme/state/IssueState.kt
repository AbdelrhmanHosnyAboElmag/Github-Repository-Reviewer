package com.example.githubrepoview.ui.theme.state

import com.example.githubrepoview.domain.model.RepoIssueItem

data class IssueState (
    val isLoading:Boolean =false,
    val repoList: MutableList<RepoIssueItem> = mutableListOf(),
    val error:String =  ""
)
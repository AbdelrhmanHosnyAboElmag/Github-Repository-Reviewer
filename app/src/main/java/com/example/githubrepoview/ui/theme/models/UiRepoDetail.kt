package com.example.githubrepoview.ui.theme.models

data class UiRepoDetail(
    val description: String? = "",
    val forks_count: Int = 0,
    val name: String = "",
    val node_id: String = "",
    val owner: UiOwner = UiOwner(),
    val stargazers_count: Int = 0
)

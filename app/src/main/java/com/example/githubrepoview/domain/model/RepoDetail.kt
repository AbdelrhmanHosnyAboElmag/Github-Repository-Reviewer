package com.example.githubrepoview.domain.model

data class RepoDetail(
    val description: String = "",
    val forks_count: Int = 0,
    val name: String = "",
    val node_id: String = "",
    val owner: Owner = Owner(),
    val stargazers_count: Int = 0
)
package com.example.githubrepoview.domain.model

data class RepoIssueItem(
    val created_at: String="",
    val id: Int=0,
    val state: String="",
    val title: String="",
    val user: User = User()
)
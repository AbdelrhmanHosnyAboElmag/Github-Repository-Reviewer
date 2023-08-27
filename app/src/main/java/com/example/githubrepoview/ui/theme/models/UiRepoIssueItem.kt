package com.example.githubrepoview.ui.theme.models

import com.example.githubrepoview.domain.model.User

data class UiRepoIssueItem(
    val created_at: String = "",
    val id: Int = 0,
    val state: String = "",
    val title: String = "",
    val user: UiUser = UiUser()
)

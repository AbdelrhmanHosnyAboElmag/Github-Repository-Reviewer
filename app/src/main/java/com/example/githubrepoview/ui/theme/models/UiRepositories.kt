package com.example.githubrepoview.ui.theme.models


data class UiRepositories(
    val description: String? = "",
    val full_name: String = "",
    val id: Int = 0,
    val name: String = "",
    val owner: UiOwner = UiOwner(),
    val node_id: String
)
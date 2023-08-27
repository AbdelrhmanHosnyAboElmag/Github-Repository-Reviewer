package com.example.githubrepoview.domain.model

data class RepositoriesItem(
    val description: String?="",
    val full_name: String="",
    val id: Int=0,
    val name: String="",
    val owner: Owner = Owner(),
    val node_id:String
)
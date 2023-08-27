package com.example.githubrepoview.domain.model

data class Item(
    val description: String?="",
    val fork: Boolean=false,
    val forks: Int=0,
    val full_name: String="",
    val id: Int=0,
    val name: String="",
    val node_id: String="",
    val owner: Owner=Owner(),
)
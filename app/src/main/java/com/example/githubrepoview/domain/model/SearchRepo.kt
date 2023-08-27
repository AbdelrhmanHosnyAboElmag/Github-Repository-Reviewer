package com.example.githubrepoview.domain.model

data class SearchRepo(
    val incomplete_results: Boolean =false,
    val items: List<Item> = listOf(Item()),
    val total_count: Int =0
)
package com.example.githubrepoview.repositories

import com.example.githubrepoview.data.local.entity.RepoDetailsDatabase
import com.example.githubrepoview.data.local.entity.RepositoriesDatabase
import com.example.githubrepoview.domain.model.RepoDetail
import com.example.githubrepoview.domain.model.RepoIssueItem
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.domain.model.SearchRepo


interface RepositoriesItemRepository {
    suspend fun getRepositories(): MutableList<RepositoriesItem>
    suspend fun getRepositoriesDatabase(): MutableList<RepositoriesDatabase>
    suspend fun getRepositoryDetail(owner: String, repo: String): RepoDetail
    suspend fun getRepositoryIssue(owner: String, repo: String): MutableList<RepoIssueItem>
    suspend fun getRepositoriesDetailDatabase(nodeId: String): RepoDetailsDatabase
    suspend fun getRepositoriesSearch(query: String): SearchRepo

}
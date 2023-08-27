package com.example.githubrepoview.data.repository

import com.example.githubrepoview.data.local.entity.RepoDetailsDatabase
import com.example.githubrepoview.data.local.entity.RepositoriesDatabase
import com.example.githubrepoview.domain.model.RepoDetail
import com.example.githubrepoview.domain.model.RepoIssueItem
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.domain.model.SearchRepo
import com.example.githubrepoview.repositories.RepositoriesItemRepository
import com.example.githubrepoview.utils.DataResult

class FakeRepoItemRepository : RepositoriesItemRepository {
    var RepositoriesItem = mutableListOf<RepositoriesItem>()
    var RepositoriesItemDatabase = mutableListOf<RepositoriesDatabase>()
    var RepositoriesDetailItem = RepoDetail()
    var RepositoriesDetailDatabaseItem = RepoDetailsDatabase()
    var RepositoriesSearchItem = SearchRepo()

    var RepositoriesIssueItem = mutableListOf<RepoIssueItem>()

    // Simulate an error in  repository functions
    var shouldThrowError = false

    override suspend fun getRepositories(): MutableList<RepositoriesItem> {
        if (shouldThrowError) {
            throw Exception("Simulated error")
        }
        return RepositoriesItem
    }

    override suspend fun getRepositoriesDatabase(): MutableList<RepositoriesDatabase> {
        return RepositoriesItemDatabase
    }

    override suspend fun getRepositoryDetail(owner: String, repo: String): RepoDetail {
        if (shouldThrowError) {
            throw Exception("Simulated error")
        }
        return RepositoriesDetailItem
    }

    override suspend fun getRepositoryIssue(
        owner: String,
        repo: String
    ): MutableList<RepoIssueItem> {
        return RepositoriesIssueItem
    }

    override suspend fun getRepositoriesDetailDatabase(nodeId: String): RepoDetailsDatabase {
        return RepositoriesDetailDatabaseItem
    }

    override suspend fun getRepositoriesSearch(query: String): SearchRepo {
        return RepositoriesSearchItem
    }

}
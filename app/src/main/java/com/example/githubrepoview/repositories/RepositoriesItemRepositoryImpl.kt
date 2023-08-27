package com.example.githubrepoview.repositories

import com.example.githubrepoview.domain.model.RepoDetail
import com.example.githubrepoview.domain.model.RepoIssueItem
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.data.api.RepositoriesApi
import com.example.githubrepoview.data.local.database.DatabaseRepositories
import com.example.githubrepoview.data.local.entity.RepoDetailsDatabase
import com.example.githubrepoview.data.local.entity.RepositoriesDatabase
import com.example.githubrepoview.domain.model.SearchRepo
import com.example.githubrepoview.utils.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RepositoriesItemRepositoryImpl @Inject constructor(
    private val repositoriesApi: RepositoriesApi,
    private val database: DatabaseRepositories
) : RepositoriesItemRepository {
    override suspend fun getRepositories(): MutableList<RepositoriesItem> {
        var repo: MutableList<RepositoriesItem>
        withContext(Dispatchers.IO) {
            repo = repositoriesApi.getRepositories().await()
            val repoEntities = Mapper.mapRepositoriesToEntity(repositoriesItem = repo)
            database.RepositoriesDao.insertAll(product = repoEntities)
        }
        return repo
    }

    override suspend fun getRepositoriesDatabase(): MutableList<RepositoriesDatabase> {
        var repoDatabase: MutableList<RepositoriesDatabase>
        withContext(Dispatchers.IO) {
            repoDatabase = database.RepositoriesDao.getRepositories()
        }
        return repoDatabase
    }

    override suspend fun getRepositoryDetail(owner: String, repo: String): RepoDetail {
        var repoDetail: RepoDetail
        withContext(Dispatchers.IO) {
            repoDetail = repositoriesApi.getRepositoryDetail(owner, repo).await()
            val repoDetailEntities =
                Mapper.mapRepoDetailToEntity(repositoriesItem = repoDetail)
            database.RepoDetailDao.insert(repoDetails = repoDetailEntities)

        }
        return repoDetail
    }

    override suspend fun getRepositoryIssue(
        owner: String,
        repo: String
    ): MutableList<RepoIssueItem> {
        var repoDetail: MutableList<RepoIssueItem>
        withContext(Dispatchers.IO) {
            repoDetail = repositoriesApi.getRepositoryIssue(owner, repo).await()
        }
        return repoDetail
    }

    override suspend fun getRepositoriesDetailDatabase(nodeId: String): RepoDetailsDatabase {
        var repoDatabase: RepoDetailsDatabase
        withContext(Dispatchers.IO) {
            repoDatabase = database.RepoDetailDao.searchRepositoryByNodeId(nodeId)

        }
        return repoDatabase
    }

    override suspend fun getRepositoriesSearch(query: String): SearchRepo {
        var repoSearch: SearchRepo
        withContext(Dispatchers.IO) {
            repoSearch = repositoriesApi.getRepositoryBySearch(query).await()

        }
        return repoSearch
    }

}
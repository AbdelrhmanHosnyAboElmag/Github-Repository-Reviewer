package com.example.githubrepoview.data.api

import com.example.githubrepoview.domain.model.RepoDetail
import com.example.githubrepoview.domain.model.RepoIssueItem
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.domain.model.SearchRepo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoriesApi {
    @GET("repositories")
    fun getRepositories(): Deferred<MutableList<RepositoriesItem>>

    @GET("repos/{owner}/{repo}")
    fun getRepositoryDetail(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Deferred<RepoDetail>

    @GET("repos/{owner}/{repo}/issues")
    fun getRepositoryIssue(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Deferred<MutableList<RepoIssueItem>>

    @GET("search/repositories")
    fun getRepositoryBySearch(
        @Query("q") query: String
    ): Deferred<SearchRepo>

}
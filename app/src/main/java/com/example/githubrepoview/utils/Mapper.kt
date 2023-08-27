package com.example.githubrepoview.utils

import com.example.githubrepoview.domain.model.Owner
import com.example.githubrepoview.domain.model.RepoDetail
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.data.local.entity.OwnerDatabase
import com.example.githubrepoview.data.local.entity.RepoDetailsDatabase
import com.example.githubrepoview.data.local.entity.RepositoriesDatabase
import com.example.githubrepoview.domain.model.Item
import com.example.githubrepoview.domain.model.RepoIssueItem
import com.example.githubrepoview.domain.model.SearchRepo
import com.example.githubrepoview.ui.theme.models.UiOwner
import com.example.githubrepoview.ui.theme.models.UiRepoDetail
import com.example.githubrepoview.ui.theme.models.UiRepoIssueItem
import com.example.githubrepoview.ui.theme.models.UiRepositories
import com.example.githubrepoview.ui.theme.models.UiUser


object Mapper {
    fun mapRepositoriesToEntity(repositoriesItem: MutableList<RepositoriesItem>): Array<RepositoriesDatabase> {
        return repositoriesItem.map { repoItem ->
            RepositoriesDatabase(
                owner = OwnerDatabase(
                    ownerName = repoItem.owner.login,
                    avater = repoItem.owner.avatar_url
                ),
                description = repoItem.description ?: "",
                userName = repoItem.name,
                nodeId = repoItem.node_id
            )
        }.toTypedArray()
    }

    fun mapRepoDetailToEntity(repositoriesItem: RepoDetail): RepoDetailsDatabase {
        return RepoDetailsDatabase(
            owner = OwnerDatabase(
                ownerName = repositoriesItem.owner.login,
                avater = repositoriesItem.owner.avatar_url
            ),
            description = repositoriesItem.description ?: "",
            userName = repositoriesItem.name,
            starCount = repositoriesItem.stargazers_count,
            forkCount = repositoriesItem.forks_count,
            nodeId = repositoriesItem.node_id
        )
    }

    fun mapEntityToRepositories(repositoriesItem: MutableList<RepositoriesDatabase>): Array<RepositoriesItem> {
        return repositoriesItem.map { repoItem ->
            RepositoriesItem(
                owner = Owner(login = repoItem.owner.ownerName, avatar_url = repoItem.owner.avater),
                description = repoItem.description ?: "",
                name = repoItem.userName,
                node_id = repoItem.nodeId
            )
        }.toTypedArray()
    }


    fun mapRepositoriesItemDatabaseToUiRepositories(repositoriesDatabaseItem: MutableList<RepositoriesDatabase>): Array<UiRepositories> {
        return repositoriesDatabaseItem.map { repoItem ->
            UiRepositories(
                owner = UiOwner(
                    login = repoItem.owner.ownerName,
                    avatar_url = repoItem.owner.avater
                ),
                description = repoItem.description ?: "",
                name = repoItem.userName,
                node_id = repoItem.nodeId
            )
        }.toTypedArray()
    }

    fun mapRepositoriesItemToUiRepositories(repositoriesItem: MutableList<RepositoriesItem>): Array<UiRepositories> {
        return repositoriesItem.map {
            UiRepositories(
                description = it.description ?: "",
                full_name = it.full_name,
                id = it.id,
                name = it.name,
                owner = UiOwner(
                    login = it.owner.login,
                    avatar_url = it.owner.avatar_url
                ),
                node_id = it.node_id
            )
        }.toTypedArray()
    }

    fun mapRepositoriesItemToUiRepositoriesSearch(repositoriesItem: List<Item>): Array<UiRepositories> {
        return repositoriesItem.map {
            UiRepositories(
                description = it.description ?: "",
                full_name = it.full_name,
                id = it.id,
                name = it.name,
                owner = UiOwner(
                    login = it.owner.login,
                    avatar_url = it.owner.avatar_url
                ),
                node_id = it.node_id
            )
        }.toTypedArray()
    }


    fun mapRepoDetailToUiRepoDetail(repoDetail: RepoDetail): UiRepoDetail {
        return UiRepoDetail(
            description = repoDetail.description,
            forks_count = repoDetail.forks_count,
            name = repoDetail.name,
            node_id = repoDetail.node_id,
            owner = UiOwner(
                login = repoDetail.owner.login,
                avatar_url = repoDetail.owner.avatar_url
            ),
            stargazers_count = repoDetail.stargazers_count
        )
    }

    fun mapEntityToRepoDetailUiRepoDetail(
        repositoriesDetailItem:
        RepoDetailsDatabase
    ): UiRepoDetail {
        return UiRepoDetail(
            description = repositoriesDetailItem.description,
            forks_count = repositoriesDetailItem.forkCount,
            name = repositoriesDetailItem.userName,
            node_id = repositoriesDetailItem.nodeId,
            owner = UiOwner(
                login = repositoriesDetailItem.owner.ownerName,
                avatar_url = repositoriesDetailItem.owner.avater
            ),
            stargazers_count = repositoriesDetailItem.starCount
        )
    }

    fun mapRepoIssueItemToUiRepoIssueItem(repoIssueItem: RepoIssueItem): UiRepoIssueItem {
        return UiRepoIssueItem(
            created_at = repoIssueItem.created_at,
            id = repoIssueItem.id,
            state = repoIssueItem.state,
            title = repoIssueItem.title,
            user = UiUser(
                login = repoIssueItem.user.login,
                id = repoIssueItem.user.id
            )
        )
    }
}
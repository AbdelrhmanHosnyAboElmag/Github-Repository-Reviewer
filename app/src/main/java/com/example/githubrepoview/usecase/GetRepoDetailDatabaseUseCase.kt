package com.example.githubrepoview.usecase

import com.example.githubrepoview.data.local.entity.RepoDetailsDatabase
import com.example.githubrepoview.domain.model.RepoDetail
import com.example.githubrepoview.repositories.RepositoriesItemRepository
import com.example.githubrepoview.repositories.RepositoriesItemRepositoryImpl
import com.example.githubrepoview.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRepoDetailDatabaseUseCase @Inject constructor(private val repositories: RepositoriesItemRepository) {
    operator fun invoke(nodeId:String): Flow<DataResult<RepoDetailsDatabase>> = flow {
        try {
            emit(DataResult.Loading())
            val repo = repositories.getRepositoriesDetailDatabase(nodeId)
            emit(DataResult.Success(repo))
        }catch (e:Exception){
            emit(DataResult.Error( "error occurred,please check network"))
        }
    }
}
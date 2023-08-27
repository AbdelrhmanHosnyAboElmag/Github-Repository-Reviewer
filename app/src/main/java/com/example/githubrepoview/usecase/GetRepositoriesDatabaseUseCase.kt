package com.example.githubrepoview.usecase

import com.example.githubrepoview.data.local.entity.RepositoriesDatabase
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.repositories.RepositoriesItemRepository
import com.example.githubrepoview.repositories.RepositoriesItemRepositoryImpl
import com.example.githubrepoview.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRepositoriesDatabaseUseCase @Inject constructor(private val repositories: RepositoriesItemRepository) {
    operator fun invoke(): Flow<DataResult<MutableList<RepositoriesDatabase>>> = flow {
        try {
            emit(DataResult.Loading())
            val repo = repositories.getRepositoriesDatabase()
            emit(DataResult.Success(repo))
        }catch (e:Exception){
            emit(DataResult.Error( "error occurred,please check network"))
        }
    }
}
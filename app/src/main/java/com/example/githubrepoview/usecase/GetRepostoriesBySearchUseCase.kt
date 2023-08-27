package com.example.githubrepoview.usecase

import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.domain.model.SearchRepo
import com.example.githubrepoview.repositories.RepositoriesItemRepository
import com.example.githubrepoview.utils.DataResult
import com.example.githubrepoview.utils.HttpStatusMessageResolver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GetRepostoriesBySearchUseCase @Inject constructor(private val repositories: RepositoriesItemRepository) {
    operator fun invoke(query:String): Flow<DataResult<SearchRepo>> = flow {
        try {
            emit(DataResult.Loading())
            val repo = repositories.getRepositoriesSearch(query)
            emit(DataResult.Success(repo))
        }catch (e: HttpException) {
            emit(DataResult.Error(HttpStatusMessageResolver.resolveMessage(e.code())))
        } catch (e: UnknownHostException) {
            emit(DataResult.Error("No network connection"))
        } catch (e: SocketTimeoutException) {
            emit(DataResult.Error("Network request timed out"))
        } catch (e: Exception) {
            emit(DataResult.Error("An error occurred: ${e.message}"))
        }
    }
}
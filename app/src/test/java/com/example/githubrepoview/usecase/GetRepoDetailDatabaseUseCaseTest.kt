package com.example.githubrepoview.usecase

import com.example.githubrepoview.data.local.entity.OwnerDatabase
import com.example.githubrepoview.data.local.entity.RepoDetailsDatabase
import com.example.githubrepoview.data.repository.FakeRepoItemRepository
import com.example.githubrepoview.utils.DataResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRepoDetailDatabaseUseCaseTest{
    private lateinit var getRepo: GetRepoDetailDatabaseUseCase
    private lateinit var fakeRepository: FakeRepoItemRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepoItemRepository()
        getRepo = GetRepoDetailDatabaseUseCase(fakeRepository)
    }

    @Test
    fun `get Repositories Detail that start with node id 0`() = runBlocking {
        fakeRepository.RepositoriesDetailDatabaseItem =
            RepoDetailsDatabase(
                userName = "Repo ",
                owner = OwnerDatabase(),
                description = "asdsadas",
                nodeId = "0"
            )
        val result = getRepo.invoke("")
        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    assertEquals(dataResult.data?.nodeId, "0")
                }

                is DataResult.Loading -> {}
                is DataResult.Error -> {}
            }
        }
    }


    @Test
    fun `get Repositories Detail Error`() = runBlocking {
        fakeRepository.shouldThrowError = true

        val result = getRepo.invoke("")

        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                }

                is DataResult.Loading -> {
                }

                is DataResult.Error -> {
                    assertEquals(dataResult.message, "An error occurred: Simulated error")
                }
            }
        }
    }
}
package com.example.githubrepoview.usecase

import com.example.githubrepoview.data.local.entity.OwnerDatabase
import com.example.githubrepoview.data.local.entity.RepositoriesDatabase
import com.example.githubrepoview.data.repository.FakeRepoItemRepository
import com.example.githubrepoview.domain.model.Owner
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.utils.DataResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRepositoriesDatabaseUseCaseTest{
    private lateinit var getRepo: GetRepositoriesDatabaseUseCase
    private lateinit var fakeRepository: FakeRepoItemRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepoItemRepository()
        getRepo = GetRepositoriesDatabaseUseCase(fakeRepository)
    }

    @Test
    fun `get Repositories that start with id 0`() = runBlocking {
        fakeRepository.RepositoriesItemDatabase = mutableListOf<RepositoriesDatabase>().apply {
            repeat(5) { index ->
                add(
                    RepositoriesDatabase(
                        id = 0L,
                        userName = "",
                        owner = OwnerDatabase(),
                        description = "asdsadas${index}",
                        nodeId = "!@3123"
                    )
                )
            }
        }
        val result = getRepo.invoke()
        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    assertEquals(dataResult.data?.get(0)?.id, 0L)
                }
                is DataResult.Loading -> {}
                is DataResult.Error -> {}
            }
        }
    }


    @Test
    fun `get Repositories Error`() = runBlocking {
        fakeRepository.shouldThrowError = true

        val result = getRepo.invoke()

        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                }
                is DataResult.Loading -> {
                }
                is DataResult.Error -> {
                    assertEquals(dataResult.message, "Simulated error")
                }
            }
        }
    }
}
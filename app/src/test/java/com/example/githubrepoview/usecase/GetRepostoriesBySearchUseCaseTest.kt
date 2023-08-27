package com.example.githubrepoview.usecase

import com.example.githubrepoview.data.repository.FakeRepoItemRepository
import com.example.githubrepoview.domain.model.Item
import com.example.githubrepoview.domain.model.Owner
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.domain.model.SearchRepo
import com.example.githubrepoview.utils.DataResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRepostoriesBySearchUseCaseTest {
    private lateinit var getRepo: GetRepostoriesBySearchUseCase
    private lateinit var fakeRepository: FakeRepoItemRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepoItemRepository()
        getRepo = GetRepostoriesBySearchUseCase(fakeRepository)
    }

    @Test
    fun `get Repositories by search that start with id 0`() = runBlocking {
        fakeRepository.RepositoriesSearchItem = SearchRepo(items = listOf(Item(id = 0)))
        val result = getRepo.invoke("")
        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    assertEquals(dataResult.data?.items?.get(0)?.id, 0)
                }

                is DataResult.Loading -> {}
                is DataResult.Error -> {}
            }
        }
    }


    @Test
    fun `get Repositories by search Error`() = runBlocking {
        fakeRepository.shouldThrowError = true

        val result = getRepo.invoke("")

        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    // You can add assertions for the success state if needed
                }

                is DataResult.Loading -> {
                    // You can add assertions for the loading state if needed
                }

                is DataResult.Error -> {
                    // Assert that the error state is received
                    assertEquals(dataResult.message, "Simulated error")
                }
            }
        }
    }
}
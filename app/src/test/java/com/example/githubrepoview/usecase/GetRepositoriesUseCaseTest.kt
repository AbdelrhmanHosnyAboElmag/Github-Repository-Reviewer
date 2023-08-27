package com.example.githubrepoview.usecase

import com.example.githubrepoview.data.repository.FakeRepoItemRepository
import com.example.githubrepoview.domain.model.Owner
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.utils.DataResult
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.assertEquals


class GetRepositoriesUseCaseTest {
    private lateinit var getRepo: GetRepositoriesUseCase
    private lateinit var fakeRepository: FakeRepoItemRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepoItemRepository()
        getRepo = GetRepositoriesUseCase(fakeRepository)
    }

    @Test
    fun `get Repositories that start with id 0`() = runBlocking {
        fakeRepository.RepositoriesItem = mutableListOf<RepositoriesItem>().apply {
            repeat(5) { index ->
                add(
                    RepositoriesItem(
                        id = index,
                        name = "Repo $index",
                        owner = Owner(login = "Owner $index"),
                        description = "asdsadas${index}",
                        node_id = "!@3123"
                    )
                )
            }
        }
        val result = getRepo.invoke()
        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    assertEquals(dataResult.data?.get(0)?.id, 0)
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


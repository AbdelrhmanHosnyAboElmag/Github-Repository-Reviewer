package com.example.githubrepoview.usecase

import com.example.githubrepoview.data.repository.FakeRepoItemRepository
import com.example.githubrepoview.domain.model.Owner
import com.example.githubrepoview.domain.model.RepoDetail
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.utils.DataResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRepoDetailUseCaseTest {
    private lateinit var getRepo: GetRepoDetailUseCase
    private lateinit var fakeRepository: FakeRepoItemRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepoItemRepository()
        getRepo = GetRepoDetailUseCase(fakeRepository)
    }

    @Test
    fun `get Repositories Detail that start with node id 0`() = runBlocking {
        fakeRepository.RepositoriesDetailItem =
            RepoDetail(
                name = "Repo ",
                owner = Owner(login = "Owner "),
                description = "asdsadas",
                node_id = "0"
            )
        val result = getRepo.invoke("", "")
        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    assertEquals(dataResult.data?.node_id, "0")
                }

                is DataResult.Loading -> {}
                is DataResult.Error -> {}
            }
        }
    }


    @Test
    fun `get Repositories Detail Error`() = runBlocking {
        fakeRepository.shouldThrowError = true

        val result = getRepo.invoke("", "")

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
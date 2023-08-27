package com.example.githubrepoview.usecase

import com.example.githubrepoview.data.repository.FakeRepoItemRepository
import com.example.githubrepoview.domain.model.Owner
import com.example.githubrepoview.domain.model.RepoDetail
import com.example.githubrepoview.domain.model.RepoIssueItem
import com.example.githubrepoview.domain.model.User
import com.example.githubrepoview.utils.DataResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetIssueUseCaseTest {
    private lateinit var getRepo: GetIssueUseCase
    private lateinit var fakeRepository: FakeRepoItemRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepoItemRepository()
        getRepo = GetIssueUseCase(fakeRepository)
    }

    @Test
    fun `get Repositories issue that has state closed`() = runBlocking {
        fakeRepository.RepositoriesIssueItem = mutableListOf<RepoIssueItem>().apply {
            (
                    repeat(5) { index ->
                        add(
                            RepoIssueItem(
                                created_at = "time",
                                id = 0,
                                state = "closed",
                                title = "title",
                                user = User()
                            )
                        )
                    })
        }
        val result = getRepo.invoke("", "")
        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    assertEquals(dataResult.data?.get(0)?.state, "closed")
                }

                is DataResult.Loading -> {}
                is DataResult.Error -> {}
            }
        }
    }


    @Test
    fun `get Repositories issue Error`() = runBlocking {
        fakeRepository.shouldThrowError = true

        val result = getRepo.invoke("","")

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
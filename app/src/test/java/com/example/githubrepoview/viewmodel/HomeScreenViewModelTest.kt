package com.example.githubrepoview.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubrepoview.MainCoroutineRule
import com.example.githubrepoview.ui.theme.state.RepoListState
import com.example.githubrepoview.domain.model.RepositoriesItem
import com.example.githubrepoview.ui.theme.viewmodel.HomeScreenViewModel
import com.example.githubrepoview.usecase.GetRepositoriesDatabaseUseCase
import com.example.githubrepoview.usecase.GetRepositoriesUseCase
import com.example.githubrepoview.usecase.GetRepostoriesBySearchUseCase
import com.example.githubrepoview.utils.DataResult
import com.example.githubrepoview.utils.Mapper.mapRepositoriesItemToUiRepositories
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeScreenViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private var getRepositoriesUseCase = mock<GetRepositoriesUseCase>()
    private var getRepositoriesBySearchUseCase = mock<GetRepostoriesBySearchUseCase>()

    private var getRepositoriesDatabaseUseCase = mock<GetRepositoriesDatabaseUseCase>()
    private lateinit var viewModel: HomeScreenViewModel


    @Test
    fun `loadRepositoriesFromApi should update repoState with data when successful`() =
        runBlocking {
            val mockRepoList = mutableListOf<RepositoriesItem>()
            val successResult = DataResult.Success(mockRepoList)
            val flowResult = flowOf(successResult)
            whenever(getRepositoriesUseCase.invoke()).thenReturn(flowResult)
            viewModel = HomeScreenViewModel(getRepositoriesUseCase, getRepositoriesDatabaseUseCase,getRepositoriesBySearchUseCase)

            val expectedState = RepoListState(false, mapRepositoriesItemToUiRepositories(mockRepoList), "error")
            val actualState = viewModel.repoState.value
            assertEquals(expectedState.repoList, (actualState as RepoListState).repoList)
        }

    @Test
    fun `loadRepositoriesFromApi should update repoState with error when API call fails`() =
        runBlocking {
            val errorMessage = "Failed to fetch data"
            val errorResult = DataResult.Error<MutableList<RepositoriesItem>>(errorMessage)
            val flowResult = flowOf(errorResult)
            whenever(getRepositoriesUseCase.invoke()).thenReturn(flowResult)
            viewModel = HomeScreenViewModel(getRepositoriesUseCase, getRepositoriesDatabaseUseCase,getRepositoriesBySearchUseCase)
            val expectedState = RepoListState(false, emptyArray(), errorMessage)
            val actualState = viewModel.repoState.value
            assertEquals(expectedState.error, (actualState as RepoListState).error)
        }
}
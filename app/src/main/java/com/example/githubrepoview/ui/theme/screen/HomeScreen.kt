package com.example.githubrepoview.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubrepoview.R
import com.example.githubrepoview.ui.theme.state.RepoListState
import com.example.githubrepoview.ui.theme.compose.CardContent
import com.example.githubrepoview.ui.theme.compose.SearchEditText
import com.example.githubrepoview.ui.theme.models.UiRepositories
import com.example.githubrepoview.ui.theme.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    onNavigateToDetailScreen: (String,String,String) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val repoState = viewModel.repoState.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchEditText(viewModel)
        DisplayRepoList(repoState, onNavigateToDetailScreen)
    }
}

@Composable
fun DisplayRepoList(
    repoState: RepoListState,
    onNavigateToDetailScreen: (String,String,String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            repoState.isLoading -> {
                CircularProgressIndicator(
                    color = Color.Red,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(48.dp)
                )
            }

            !repoState.error.isNullOrEmpty() -> {
                Toast.makeText(LocalContext.current, repoState.error + "", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {

                repoState.repoList?.let { RepoList(it,  onNavigateToDetailScreen ) }
            }
        }
    }
}

@Composable
fun RepoList(
    repositories: Array<UiRepositories>,
    selectedRepoItem:  (String,String,String) -> Unit
) {
    LazyColumn {
        items(repositories) { repo ->
            ItemCard(data = repo, selectedRepoItem = selectedRepoItem)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(data: UiRepositories, selectedRepoItem: (String, String, String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.red_white),
        ),
        onClick = { selectedRepoItem(data.owner.login,data.name,data.node_id) }
    ) {
        CardContent(data)
    }
}





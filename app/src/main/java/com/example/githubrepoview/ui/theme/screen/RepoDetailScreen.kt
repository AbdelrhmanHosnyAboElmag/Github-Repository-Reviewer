package com.example.githubrepoview.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubrepoview.R
import com.example.githubrepoview.ui.theme.compose.loadImageWithCoil
import com.example.githubrepoview.ui.theme.composestyle.headTextStyle
import com.example.githubrepoview.ui.theme.composestyle.infoTextStyle
import com.example.githubrepoview.ui.theme.models.UiRepoDetail
import com.example.githubrepoview.ui.theme.viewmodel.RepoDetailViewModel

@Composable
fun RepoDetailScreen(
    onNavigateToIssueScreen: (String, String) -> Unit,
    owner: String,
    repo: String,
    nodeId: String,
    viewModel: RepoDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel) {
        viewModel.loadRepoDetailFromApi(owner, repo, nodeId)
    }
    val repoDetailState = viewModel.repoDetailState.value

    when {
        repoDetailState.isLoading -> {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 2.dp,
                modifier = Modifier.size(48.dp)
            )
        }

        !repoDetailState.error.isNullOrEmpty() -> {
            Toast.makeText(LocalContext.current, repoDetailState.error + "", Toast.LENGTH_SHORT)
                .show()
        }

        else -> {
            repoDetailState.repoList?.let { ContentView(it, onNavigateToIssueScreen, owner, repo) }
        }
    }
}

@Composable
private fun ContentView(
    repoList: UiRepoDetail, onNavigateToIssueScreen: (String, String) -> Unit, owner: String,
    repo: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = loadImageWithCoil(repoList.owner.avatar_url),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .align(alignment = Alignment.TopCenter)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)

            ) {
                Text(text = "RepoName:", style = headTextStyle())
                Text(text = repoList.name, style = infoTextStyle())
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "Repo Description:", style = headTextStyle(),
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                Text(
                    text = repoList.description,
                    style = infoTextStyle()
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star_full),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                    )

                    Text(
                        text = repoList.stargazers_count.toString(),
                        modifier = Modifier
                            .padding(top = 8.dp, start = 8.dp), style = infoTextStyle()
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = "fork:", style = headTextStyle(),
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Text(
                    text = repoList.forks_count.toString(),
                    modifier = Modifier
                        .padding(top = 8.dp), style = infoTextStyle()
                )
            }
        }

        Button(
            onClick = { onNavigateToIssueScreen(owner,repo) },
            colors = ButtonDefaults.buttonColors(
                colorResource(R.color.red_white)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "Issue")
        }
    }
}



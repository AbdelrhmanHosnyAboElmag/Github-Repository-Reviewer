package com.example.githubrepoview.ui.theme.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubrepoview.domain.model.RepoIssueItem
import com.example.githubrepoview.ui.theme.composestyle.headTextStyle
import com.example.githubrepoview.ui.theme.composestyle.infoTextStyle
import com.example.githubrepoview.ui.theme.viewmodel.IssueScreenViewModel
import com.example.githubrepoview.utils.DateUtils

@Composable
fun IssueScreen(
    owner: String,
    repo: String,
    viewModel: IssueScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel) {
        viewModel.loadRepoIssueFromApi(owner, repo)
    }

    val repoIssueState = viewModel.repoIssueState.value
    when {
        repoIssueState.isLoading -> {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 2.dp,
                modifier = Modifier.size(48.dp)
            )
        }

        !repoIssueState.error.isNullOrEmpty() -> {
            Toast.makeText(LocalContext.current, repoIssueState.error + "", Toast.LENGTH_SHORT)
                .show()
        }

        else -> {
            if (!repoIssueState.repoList.isNullOrEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(repoIssueState.repoList) { repoIssue ->
                        RepoIssueItem(repoIssue = repoIssue)
                    }
                }
            }else{
                Text(text = "No issue found")
            }
        }
    }
}

@Composable
fun RepoIssueItem(repoIssue: RepoIssueItem) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (titleLabel, authorLabel, dateLabel, titleText, authorText, dateText, stateText) = createRefs()

        Text(
            text = "title:", style = headTextStyle(),
            modifier = Modifier.constrainAs(titleLabel) {
                start.linkTo(parent.start)
                top.linkTo(parent.top, margin = 24.dp)
            }
        )

        Text(
            text = "author:", style = headTextStyle(),
            modifier = Modifier.constrainAs(authorLabel) {
                start.linkTo(parent.start)
                top.linkTo(titleLabel.bottom, margin = 24.dp)
            }
        )

        Text(
            text = "date :", style = headTextStyle(),
            modifier = Modifier.constrainAs(dateLabel) {
                start.linkTo(parent.start)
                top.linkTo(authorLabel.bottom, margin = 24.dp)
            }
        )

        Text(
            text = "${repoIssue.title}", style = infoTextStyle(),
            modifier = Modifier.constrainAs(titleText) {
                start.linkTo(titleLabel.end, margin = 8.dp)
                top.linkTo(titleLabel.top)
                bottom.linkTo(titleLabel.bottom)
            }
        )

        Text(
            text = "${repoIssue.user.login}", style = infoTextStyle(),
            modifier = Modifier.constrainAs(authorText) {
                start.linkTo(authorLabel.end, margin = 8.dp)
                top.linkTo(authorLabel.top)
                bottom.linkTo(authorLabel.bottom)
            }
        )

        Text(
            text = "${DateUtils.convertDateFormat(repoIssue.created_at)}", style = infoTextStyle(),
            modifier = Modifier.constrainAs(dateText) {
                start.linkTo(dateLabel.end, margin = 8.dp)
                top.linkTo(dateLabel.top)
                bottom.linkTo(dateLabel.bottom)
            }
        )
        Log.d("sadsadasdc", "RepoIssueItem: ${repoIssue.created_at}")

        Column(
            modifier = Modifier.constrainAs(stateText) {
                start.linkTo(parent.start)
                top.linkTo(dateText.bottom, margin = 24.dp)
            }
        ) {
            val state = when (repoIssue.state) {
                "open" -> State.OPEN
                "closed" -> State.CLOSED
                else -> State.ALL
            }
            StateGitIssue(state = state)
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        )
    }
}



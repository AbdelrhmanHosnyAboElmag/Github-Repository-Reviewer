package com.example.githubrepoview.ui.theme.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.githubrepoview.ui.theme.models.UiRepositories

@Composable
fun RepoContent(data: UiRepositories, modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(start = 20.dp)
            .background(Color.Transparent)
    ) {
        Text(text = "OwnerName:${data.owner.login}", modifier = Modifier.padding(top = 4.dp))
        Text(text = "UserName:${data.name}", modifier = Modifier.padding(top = 4.dp))
        Text(
            text = "description:${data.description}",
            modifier = Modifier.padding(end = 10.dp, top = 4.dp))
        //RepoStarNumber()
    }
}
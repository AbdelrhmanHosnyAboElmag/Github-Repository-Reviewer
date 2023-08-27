package com.example.githubrepoview.ui.theme.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun StateGitIssue(state: State) {
    val textColor = when (state) {
        State.OPEN -> Color.Green
        State.CLOSED -> Color.Red
        State.ALL -> Color.Yellow
    }

    Text(
        text = state.name,
        color = textColor,
        fontWeight = FontWeight.Bold
    )
}

enum class State {
    OPEN,
    CLOSED,
    ALL
}
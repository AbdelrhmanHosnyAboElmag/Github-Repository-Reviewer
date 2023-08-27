package com.example.githubrepoview.ui.theme.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.githubrepoview.ui.theme.models.UiRepositories

@Composable
fun CardContent(data: UiRepositories) {
    ConstraintLayout(
        modifier = Modifier.padding(16.dp)
    ) {
        val (imageView, contentColumn) = createRefs()

        Image(
            painter = loadImageWithCoil(imageUrl = data.owner.avatar_url),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(imageView) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .size(50.dp)
                .clip(CircleShape)
        )

        RepoContent(data, modifier = Modifier.constrainAs(contentColumn) {
            start.linkTo(imageView.end, margin = 8.dp)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        })
    }
}
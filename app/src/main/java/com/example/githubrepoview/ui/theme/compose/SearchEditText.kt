package com.example.githubrepoview.ui.theme.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.githubrepoview.R
import com.example.githubrepoview.ui.theme.viewmodel.HomeScreenViewModel

@Composable
fun SearchEditText(viewModel: HomeScreenViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(16.dp)
            .background(
                color = colorResource(R.color.red_white), shape = RoundedCornerShape(20.dp)
            )
    ) {
        val (imageView, editText) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_search_foreground),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(imageView) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .size(50.dp)
        )

        var searchText by remember { mutableStateOf("") }

        BasicTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.loadRepositoriesBySearchFromApi(searchText)
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            modifier = Modifier
                .constrainAs(editText) {
                    start.linkTo(imageView.end, margin = 8.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .background(Color.Transparent)
        )
        if (searchText.isEmpty()) {
            Text(
                text = "Search",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .constrainAs(editText) {
                        start.linkTo(imageView.end, margin = 8.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(Color.Transparent)
            )
        }
    }
}
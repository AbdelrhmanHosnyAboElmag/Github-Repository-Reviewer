package com.example.githubrepoview.ui.theme.compose

import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.githubrepoview.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun loadImageWithCoil(imageUrl: String): ImagePainter {
     return rememberImagePainter(data = imageUrl,
          builder = {
               placeholder(R.drawable.ic_launcher_foreground)
          })
}
package com.example.githubrepoview.ui.theme.composestyle

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
@Composable
fun headTextStyle() = MaterialTheme.typography.bodyLarge.copy(
    fontFamily = FontFamily.Monospace,
    fontSize = 16.sp
)
@Composable
fun infoTextStyle() = MaterialTheme.typography.bodySmall.copy(
    fontFamily = FontFamily.Monospace,
    fontSize = 14.sp
)
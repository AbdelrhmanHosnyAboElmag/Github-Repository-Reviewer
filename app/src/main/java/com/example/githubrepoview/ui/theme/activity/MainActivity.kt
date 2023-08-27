package com.example.githubrepoview.ui.theme.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.githubrepoview.ui.theme.GithubRepoViewTheme

import com.example.githubrepoview.ui.theme.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme = isSystemInDarkTheme() // Get the system's dark mode setting
            GithubRepoViewTheme(darkTheme = isDarkTheme) { // Pass the dark mode setting to the theme
                AppNavHost()
            }
        }
    }
}






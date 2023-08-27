package com.example.githubrepoview.ui.theme.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubrepoview.ui.theme.screen.HomeScreen
import com.example.githubrepoview.ui.theme.screen.IssueScreen
import com.example.githubrepoview.ui.theme.screen.RepoDetailScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "home_screen") {
        composable(route = "home_screen", enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            HomeScreen(onNavigateToDetailScreen = { ownerText, repoText, nodeIdText ->
                val owner = ownerText
                val repo = repoText
                val nodeId = nodeIdText
                val routeWithArgs = "repo_detail_screen/$owner/$repo/$nodeId"
                navController.navigate(routeWithArgs)
            })
        }
        composable(route = "repo_detail_screen/{owner}/{repo}/{node_id}", enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            RepoDetailScreen(
                onNavigateToIssueScreen = { ownerText, repoText ->
                    val owner = ownerText
                    val repo = repoText
                    val routeWithArgs = "issue_screen/$owner/$repo"
                    navController.navigate(routeWithArgs)
                },
                it.arguments?.getString("owner").toString(),
                it.arguments?.getString("repo").toString(),
                it.arguments?.getString("node_id").toString()
            )
        }
        composable(route = "issue_screen/{owner}/{repo}", enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            IssueScreen(
                it.arguments?.getString("owner").toString(),
                it.arguments?.getString("repo").toString())
        }
    }
}
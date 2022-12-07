package com.composedeeplinkingguide

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.composedeeplinkingguide.ui.theme.ComposeDeepLinkingGuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDeepLinkingGuideTheme {
                val navController = rememberNavController()

                //Added for testing opening DeepLink from one app in another (link with ComposeLazyGrid app)
                NavHost(
                    navController = navController,
                    startDestination = "home") {
                    composable(route = "home") {
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = {
                                    navController.navigate("detail")
                            }
                            ) {
                                Text(text = "To detail")
                            }
                        }
                    }
                    composable(
                        route = "detail",
                        deepLinks = listOf(
                            navDeepLink {
                                //For open from the site
//                                uriPattern = "https://insta.com/{id}"
                                uriPattern = "https://insta.com/{name}"
                                action = Intent.ACTION_VIEW

                            }
                        ),
                        arguments = listOf(
//                            navArgument("id") {
//                                type = NavType.IntType
//                                defaultValue = -1
//                            }
                            navArgument("name") {
                                type = androidx.navigation.NavType.StringType
                                defaultValue = "Asya"
                            }
                        )
                    ) { entry ->
//                        val id = entry.arguments?.getInt("id")
                        val name = entry.arguments?.getString("name")
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
//                            Text(text = "The id is $id")
                            Text(text = "The id is $name")
                        }
                    }
                }
            }
        }
    }
}
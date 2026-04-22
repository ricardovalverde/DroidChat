package com.ricardovalverde.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.ricardovalverde.droidchat.ui.feature.signIn.SignInRoute
import com.ricardovalverde.droidchat.ui.feature.splash.SplashRoute
import kotlinx.serialization.Serializable


@Serializable
object SplashScreenNav

@Serializable
object SignInNav

@Serializable
object SignUpNav

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreenNav
    ) {
        composable<SplashScreenNav> {
            SplashRoute(
                onNavigateToSignIn = {
                    navController.navigate(
                        route = SignInNav,
                        navOptions = navOptions {
                            popUpTo(SplashScreenNav) {
                                inclusive = true
                            }
                        }
                    )
                }
            )
        }

        composable<SignInNav> {
            SignInRoute()
        }

        composable<SignUpNav> {

        }

    }

}
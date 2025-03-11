package org.galib.feedmenews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.galib.feedmenews.screens.detail.DetailScreen
import org.galib.feedmenews.screens.foryou.ForYouScreen
import org.galib.feedmenews.screens.home.HomeScreen
import org.galib.feedmenews.screens.interests.InterestScreen
import org.galib.feedmenews.screens.saved.SavedScreen


@Composable
fun MFNavHost(navHostController: NavHostController){

    NavHost(navController = navHostController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                navHostController,
                navigateToDetails = { objectId ->
                    navHostController.navigate(DetailDestination(objectId))
                }
            )
        }
        composable<ForYouRoute> {
            ForYouScreen (
                navHostController,
                navigateToDetails = { objectId ->
                    navHostController.navigate(DetailDestination(objectId))
                }
            )
        }
        composable<InterestRoute> {
            InterestScreen  (
                navHostController,
                navigateToDetails = { objectId ->
                    navHostController.navigate(DetailDestination(objectId))
                }
            )
        }
        composable<SavedRoute> {
            SavedScreen (
                navHostController,
                navigateToDetails = { objectId ->
                    navHostController.navigate(DetailDestination(objectId))
                }
            )
        }

        composable<DetailDestination> { backStackEntry ->
            DetailScreen(
                objectId = backStackEntry.toRoute<DetailDestination>().objectId,
                navigateBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
package org.galib.feedmenews.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import org.galib.feedmenews.navigation.ForYouRoute
import org.galib.feedmenews.navigation.HomeRoute
import org.galib.feedmenews.navigation.InterestRoute
import org.galib.feedmenews.navigation.SavedRoute


@Composable
fun MFNavigationBar(navController: NavController, currentScreen:Int) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(currentScreen)
    }
    logd("Navigation","Selected index: ${selectedNavigationIndex.intValue}")
    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = HomeRoute
        ),
        NavigationItem(
            title = "For You",
            icon = Icons.Default.Favorite,
            route = ForYouRoute
        ),
        NavigationItem(
            title = "Interest",
            icon = Icons.Default.Interests,
            route = InterestRoute
        ),
        NavigationItem(
            title = "Saved",
            icon = Icons.Default.Bookmarks,
            route = SavedRoute
        )
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    logd("Navigation","Selected index: $index")
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavigationIndex.intValue)
                            Color.Black
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    }
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: Any
)
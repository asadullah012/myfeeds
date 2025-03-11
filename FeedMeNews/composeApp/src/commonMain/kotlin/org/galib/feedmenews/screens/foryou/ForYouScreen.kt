package org.galib.feedmenews.screens.foryou

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.galib.feedmenews.navigation.MFNavigationBar
import org.galib.feedmenews.screens.EmptyScreenContent
import org.galib.feedmenews.screens.home.ObjectGrid
import org.galib.feedmenews.screens.ListViewModel
import org.galib.feedmenews.screens.MFTopAppBar
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForYouScreen(
    navController: NavHostController,
    navigateToDetails: (objectId: Int) -> Unit
) {
    val viewModel = koinViewModel<ListViewModel>()
    val objects by viewModel.objects.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            .windowInsetsPadding(WindowInsets.systemBars),
        topBar = {
            MFTopAppBar(scrollBehavior)
        },
        bottomBar = {
            MFNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            Modifier.padding(paddingValues)
        ) {
            AnimatedContent(objects.isNotEmpty()) { objectsAvailable ->
                if (objectsAvailable) {
                    ObjectGrid(
                        objects = objects,
                        onObjectClick = navigateToDetails,
                    )
                } else {
                    EmptyScreenContent(Modifier.fillMaxSize())
                }
            }
        }
    }

}
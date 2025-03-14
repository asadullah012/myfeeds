package org.galib.feedmenews.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import feedmenews.composeapp.generated.resources.Res
import feedmenews.composeapp.generated.resources.placeholder
import org.galib.feedmenews.data.MuseumObject
import org.galib.feedmenews.screens.MFNavigationBar
import org.galib.feedmenews.screens.EmptyScreenContent
import org.galib.feedmenews.screens.ListViewModel
import org.galib.feedmenews.screens.MFTopAppBar
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
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
            MFNavigationBar(navController,0)
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

@Composable
fun ObjectGrid(
    objects: List<MuseumObject>,
    onObjectClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(360.dp),
        // TODO simplify padding after https://issuetracker.google.com/issues/365052672 is fixed
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal).asPaddingValues()),
        contentPadding = WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical)
            .asPaddingValues(),
    ) {
        items(objects, key = { it.objectID }) { obj ->
            ObjectFrame(
                obj = obj,
                onClick = { onObjectClick(obj.objectID) },
            )
        }
    }
}

@Composable
private fun ObjectFrame(
    obj: MuseumObject,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = obj.thumbnail_url,
            contentDescription = obj.title,
            contentScale = ContentScale.Fit,
            error = painterResource(Res.drawable.placeholder),
            placeholder = painterResource(Res.drawable.placeholder),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
        )

        Spacer(Modifier.height(2.dp))

        Text(obj.title, style = MaterialTheme.typography.titleMedium)
        Text(obj.category, style = MaterialTheme.typography.bodyMedium)
        Text(obj.date, style = MaterialTheme.typography.bodySmall)
    }
}

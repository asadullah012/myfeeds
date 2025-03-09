package org.galib.feedmenews.screens.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import feedmenews.composeapp.generated.resources.Res
import feedmenews.composeapp.generated.resources.back
import feedmenews.composeapp.generated.resources.label_artist
import feedmenews.composeapp.generated.resources.label_credits
import feedmenews.composeapp.generated.resources.label_date
import feedmenews.composeapp.generated.resources.label_department
import feedmenews.composeapp.generated.resources.label_dimensions
import feedmenews.composeapp.generated.resources.label_medium
import feedmenews.composeapp.generated.resources.label_repository
import feedmenews.composeapp.generated.resources.label_title
import org.galib.feedmenews.data.MuseumObject
import org.galib.feedmenews.screens.EmptyScreenContent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
    objectId: Int,
    navigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<DetailViewModel>()

    val obj by viewModel.getObject(objectId).collectAsStateWithLifecycle(initialValue = null)
    AnimatedContent(obj != null) { objectAvailable ->
        if (objectAvailable) {
            ContentWebView(obj!!, onBackClick = navigateBack)
        } else {
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ContentWebView(
    obj: MuseumObject,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(Res.string.back))
                    }
                }
            )
        },
        modifier = modifier.windowInsetsPadding(WindowInsets.systemBars),
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
        ) {
            CommonWebView(obj.link)
        }
    }
}
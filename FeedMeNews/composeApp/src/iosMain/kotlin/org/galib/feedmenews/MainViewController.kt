package org.galib.feedmenews

import androidx.compose.ui.window.ComposeUIViewController
import org.galib.feedmenews.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}

package org.galib.feedmenews.screens.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun CommonWebView(mUrl: String) {
    UIKitView(
        factory = {
            WKWebView().apply {
                val url = NSURL(string = mUrl)
                loadRequest(NSURLRequest(url))
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = { webView ->
            val url = NSURL(string = mUrl)
            webView.loadRequest(NSURLRequest(url))
        },
        onRelease = { webView ->
            webView.stopLoading() // Ensures proper cleanup
        }
    )
}
package org.galib.feedmenews.data

import kotlinx.serialization.Serializable

@Serializable
data class MuseumObject(
    val objectID: Int,
    val title: String,
    val link: String,
    val description: String,
    val category: String,
    val date: String,
    val thumbnail_url: String,
    val post_format: String,
)

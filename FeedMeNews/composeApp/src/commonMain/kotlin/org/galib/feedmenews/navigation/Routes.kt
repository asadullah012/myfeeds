package org.galib.feedmenews.navigation

import kotlinx.serialization.Serializable

@Serializable data object HomeRoute
@Serializable data object ForYouRoute
@Serializable data object InterestRoute
@Serializable data object SavedRoute

@Serializable
data class DetailDestination(val objectId: Int)



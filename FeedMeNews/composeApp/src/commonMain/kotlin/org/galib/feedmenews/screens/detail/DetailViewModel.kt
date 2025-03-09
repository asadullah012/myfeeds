package org.galib.feedmenews.screens.detail

import androidx.lifecycle.ViewModel
import org.galib.feedmenews.data.MuseumObject
import org.galib.feedmenews.data.MuseumRepository
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}

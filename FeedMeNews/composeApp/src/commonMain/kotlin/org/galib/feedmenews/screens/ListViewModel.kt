package org.galib.feedmenews.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.galib.feedmenews.data.MuseumObject
import org.galib.feedmenews.data.MuseumRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListViewModel(museumRepository: MuseumRepository) : ViewModel() {
    val objects: StateFlow<List<MuseumObject>> =
        museumRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}

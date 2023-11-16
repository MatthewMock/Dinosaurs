package com.example.dinosaurs.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinosaurs.network.DinosaurPhoto
import com.example.dinosaurs.network.DinosaursApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DinosaursUiState {
    data class Success(val photos: List<DinosaurPhoto>) : DinosaursUiState
    object Error : DinosaursUiState
    object Loading : DinosaursUiState
}

class DinosaursViewModel: ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var dinosaursUiState: DinosaursUiState by mutableStateOf(DinosaursUiState.Loading)
        private set

    /** Call getDinosaurPhotos() on init so we can display status immediately. */
    init {
        getDinosaurPhotos()
    }

    /** Gets Dinosaur photos information from the Dinosaur API Retrofit service and updates
     * [DinosaurPhoto] [List] [MutableList].
     */
    fun getDinosaurPhotos() {
        viewModelScope.launch {
            dinosaursUiState = try {
                DinosaursUiState.Success(DinosaursApi.retrofitService.getPhotos())
            } catch (e: IOException) {
                DinosaursUiState.Error
            }
        }
    }
}
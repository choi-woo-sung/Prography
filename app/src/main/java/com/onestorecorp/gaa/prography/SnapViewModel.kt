package com.onestorecorp.gaa.prography

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onestorecorp.gaa.prography.data.model.Photo
import com.onestorecorp.gaa.prography.data.repsoitory.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SnapViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    //todo Linked로 바꿀것
     val photoStateFlow = mutableStateListOf<Photo>()

    init {
        fetchRandomCard()
    }


    fun fetchRandomCard() = viewModelScope.launch {
        val result = photoRepository.fetchRandomPhotoList()
        photoStateFlow.addAll(result)
    }

    fun removeCard() = viewModelScope.launch {
        photoStateFlow.removeAt(0)
    }
}
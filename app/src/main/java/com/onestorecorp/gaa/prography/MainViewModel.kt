package com.onestorecorp.gaa.prography

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onestorecorp.gaa.prography.data.repsoitory.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val photoStateFlow = photoRepository.fetchPhotoList().cachedIn(viewModelScope)



}
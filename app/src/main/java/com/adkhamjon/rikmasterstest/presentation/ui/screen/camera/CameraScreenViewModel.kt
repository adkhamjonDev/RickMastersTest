package com.adkhamjon.rikmasterstest.presentation.ui.screen.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adkhamjon.rikmasterstest.domain.Resource
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.domain.use_case.camera.CameraUseCases
import com.adkhamjon.rikmasterstest.presentation.ui.UIListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraScreenViewModel @Inject constructor(
    private val cameraUseCases: CameraUseCases
) : ViewModel() {
    private val _cameraState = MutableStateFlow(UIListState<CameraModel>())
    val camera = _cameraState.asStateFlow()

    init {
        getCameras()
    }

    fun getCameras() = viewModelScope.launch {
        cameraUseCases.getCamerasUseCase().collectLatest {
            when (it) {
                is Resource.Success -> {
                    _cameraState.value = UIListState(data = it.data)
                }

                is Resource.Error -> {
                    _cameraState.value =
                        UIListState(
                            error = it.message!!
                        )
                }

                is Resource.Loading -> {
                    _cameraState.value = UIListState(isLoading = true)
                }
            }
        }
    }
}
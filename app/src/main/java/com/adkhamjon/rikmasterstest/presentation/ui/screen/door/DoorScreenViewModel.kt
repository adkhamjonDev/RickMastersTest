package com.adkhamjon.rikmasterstest.presentation.ui.screen.door

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adkhamjon.rikmasterstest.domain.Resource
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.domain.model.DoorModel
import com.adkhamjon.rikmasterstest.domain.use_case.door.DoorUseCases
import com.adkhamjon.rikmasterstest.presentation.ui.UIListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoorScreenViewModel @Inject constructor(
    private val doorUseCases: DoorUseCases
) : ViewModel() {
    private val _doorState = MutableStateFlow(UIListState<DoorModel>())
    val door = _doorState.asStateFlow()

    init {
        getDoors()
    }

    fun getDoors() = viewModelScope.launch {
        doorUseCases.getDoorUseCase().collectLatest {
            when (it) {
                is Resource.Success -> {
                    _doorState.value = UIListState(data = it.data)
                }

                is Resource.Error -> {
                    _doorState.value =
                        UIListState(
                            error = it.message!!
                        )
                }

                is Resource.Loading -> {
                    _doorState.value = UIListState(isLoading = true)
                }
            }
        }
    }

    fun updateName(id: Int, name: String) {
        viewModelScope.launch {
            doorUseCases.updateDoorNameUseCase(id, name)
        }
    }
}
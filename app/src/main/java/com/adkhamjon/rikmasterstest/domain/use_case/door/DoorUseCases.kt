package com.adkhamjon.rikmasterstest.domain.use_case.door

import javax.inject.Inject

data class DoorUseCases @Inject constructor(
    val getDoorUseCase: GetDoorsUseCase,
    val updateDoorNameUseCase: UpdateDoorNameUseCase
)

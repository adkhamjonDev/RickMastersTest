package com.adkhamjon.rikmasterstest.domain.use_case.camera

import javax.inject.Inject

data class CameraUseCases @Inject constructor(
    val getCamerasUseCase: GetCamerasUseCase
)

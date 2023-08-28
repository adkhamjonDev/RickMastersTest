package com.adkhamjon.rikmasterstest.data.remote.dto.cameras

import kotlinx.serialization.Serializable

@Serializable
data class CameraResponseDto(
    val cameras: List<CameraDto>,
    val room: List<String>
)
package com.adkhamjon.rikmasterstest.data.remote.dto.cameras

import kotlinx.serialization.Serializable

@Serializable
data class CameraDto(
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val rec: Boolean,
    val room: String? = null,
    val snapshot: String
)
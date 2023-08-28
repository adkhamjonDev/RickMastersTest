package com.adkhamjon.rikmasterstest.data.remote.dto.doors

import kotlinx.serialization.Serializable

@Serializable
data class DoorResponseDto(
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val room: String? = null,
    val snapshot: String? = null
)
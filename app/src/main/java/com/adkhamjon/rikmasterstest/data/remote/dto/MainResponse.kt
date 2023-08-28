package com.adkhamjon.rikmasterstest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MainResponse<T>(
    val data: T,
    val success: Boolean
)
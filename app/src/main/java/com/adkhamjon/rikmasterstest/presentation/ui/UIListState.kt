package com.adkhamjon.rikmasterstest.presentation.ui

data class UIListState<T>(
    val isLoading: Boolean = false,
    val data: List<T>? = null,
    val error: String = ""
)
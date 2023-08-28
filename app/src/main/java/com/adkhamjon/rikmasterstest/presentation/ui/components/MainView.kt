package com.adkhamjon.rikmasterstest.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adkhamjon.rikmasterstest.presentation.ui.theme.screenBackground

@Composable
fun MainView(
    contentView: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackground)
    ) {
        contentView.invoke()
    }
}
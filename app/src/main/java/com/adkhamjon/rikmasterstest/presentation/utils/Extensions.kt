package com.adkhamjon.rikmasterstest.presentation.utils

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.adkhamjon.rikmasterstest.presentation.ui.theme.toolBarBackgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Modifier.clickableRipple(
    onClick: () -> Unit
) = then(
    Modifier.clickable(
        interactionSource = remember { MutableInteractionSource() }, // without this indication parameter gets an error
        indication = rememberRipple(color = Color(0x1A000000)),
    ) {
        onClick.invoke()
    }
)

@Composable
fun StatusBarColor() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = toolBarBackgroundColor
    )
}

fun dipToPixels(context: Context, dipValue: Float): Float {
    return dipValue * context.resources.displayMetrics.density
}
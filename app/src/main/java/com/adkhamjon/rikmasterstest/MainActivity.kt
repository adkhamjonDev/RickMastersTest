package com.adkhamjon.rikmasterstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.adkhamjon.rikmasterstest.presentation.ui.screen.MainScreen
import com.adkhamjon.rikmasterstest.presentation.ui.theme.RikMastersTestTheme
import com.adkhamjon.rikmasterstest.presentation.ui.theme.screenBackground
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RikMastersTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = screenBackground
                ) {
                    MainScreen()
                }
            }
        }
    }
}
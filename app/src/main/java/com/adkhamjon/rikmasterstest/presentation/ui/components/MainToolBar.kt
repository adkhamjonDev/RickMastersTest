package com.adkhamjon.rikmasterstest.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.adkhamjon.rikmasterstest.R
import com.adkhamjon.rikmasterstest.presentation.ui.theme.toolBarBackgroundColor
import com.adkhamjon.rikmasterstest.presentation.ui.theme.toolBarHeight
import com.adkhamjon.rikmasterstest.presentation.ui.theme.toolBarTextColor
import com.adkhamjon.rikmasterstest.presentation.ui.theme.toolBarTextSize

@Preview(showBackground = true)
@Composable
fun MainToolBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(toolBarHeight)
            .background(toolBarBackgroundColor),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = stringResource(id = R.string.my_house),
            color = toolBarTextColor,
            fontSize = toolBarTextSize,
            fontWeight = FontWeight.Normal
        )
    }
}
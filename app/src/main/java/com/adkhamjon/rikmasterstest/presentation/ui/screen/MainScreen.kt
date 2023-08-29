package com.adkhamjon.rikmasterstest.presentation.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adkhamjon.rikmasterstest.R
import com.adkhamjon.rikmasterstest.presentation.ui.components.MainToolBar
import com.adkhamjon.rikmasterstest.presentation.ui.screen.camera.CameraScreen
import com.adkhamjon.rikmasterstest.presentation.ui.screen.door.DoorScreen
import com.adkhamjon.rikmasterstest.presentation.ui.theme.blue
import com.adkhamjon.rikmasterstest.presentation.ui.theme.screenBackground
import com.adkhamjon.rikmasterstest.presentation.ui.theme.tabRowHeight
import com.adkhamjon.rikmasterstest.presentation.ui.theme.tabTextSize
import com.adkhamjon.rikmasterstest.presentation.ui.theme.toolBarBackgroundColor
import com.adkhamjon.rikmasterstest.presentation.ui.theme.toolBarTextColor
import com.adkhamjon.rikmasterstest.presentation.utils.clickableRipple
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {

    val coroutineScope = rememberCoroutineScope()

    val tabRowTitles =
        listOf(stringResource(id = R.string.cameras), stringResource(id = R.string.doors))

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        tabRowTitles.size
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackground)
    ) {
        Column {
            MainToolBar()
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = blue,
                backgroundColor = toolBarBackgroundColor
            ) {
                tabRowTitles.forEachIndexed { index, title ->
                    CustomTabItem(
                        title = title,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
                pageSpacing = 0.dp,
                userScrollEnabled = false,
                reverseLayout = false,
                beyondBoundsPageCount = 0,
                key = null,
                pageContent = {
                    when (it) {
                        0 -> {
                            CameraScreen()
                        }

                        1 -> {
                            DoorScreen()
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun CustomTabItem(
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(tabRowHeight)
            .clickableRipple {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = toolBarTextColor,
            fontSize = tabTextSize,
            fontWeight = FontWeight.Normal
        )
    }
}

package com.adkhamjon.rikmasterstest.presentation.ui.screen.door

import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.adkhamjon.rikmasterstest.R
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.domain.model.DoorModel
import com.adkhamjon.rikmasterstest.presentation.ui.theme.blue
import com.adkhamjon.rikmasterstest.presentation.ui.theme.cardBackgroundColor
import com.adkhamjon.rikmasterstest.presentation.ui.theme.cardRadius
import com.adkhamjon.rikmasterstest.presentation.ui.theme.screenBackground
import com.adkhamjon.rikmasterstest.presentation.ui.theme.tabTextSize
import com.adkhamjon.rikmasterstest.presentation.ui.theme.toolBarTextColor
import com.adkhamjon.rikmasterstest.presentation.utils.dipToPixels
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorScreen(
    viewModel: DoorScreenViewModel = hiltViewModel()
) {
    val doorListState = viewModel.door.collectAsState().value

    val doorList = remember {
        mutableStateListOf<DoorModel>()
    }
    var progress by remember {
        mutableStateOf(true)
    }
    if (doorListState.isLoading) {
        progress = true
    }
    if (doorListState.error.isNotEmpty()) {
        progress = false
        Toast.makeText(LocalContext.current, doorListState.error, Toast.LENGTH_SHORT).show()
    }
    if (doorListState.data != null) {
        progress = false
        doorList.clear()
        doorList.addAll(doorListState.data)
    }
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        viewModel.getDoors()
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackground)
            .pullRefresh(state),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(doorList) {
                DoorItem(it) { item ->
                    viewModel.updateName(item.id, "ASDASDASDASDASD")
                    viewModel.getDoors()
                }
            }
        }
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
        if (progress) {
            CircularProgressIndicator(color = blue)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorItem(
    doorModel: DoorModel,
    onEditClick: (data: DoorModel) -> Unit
) {
    val swipeableState = rememberSwipeableState(0)
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .swipeable(
                state = swipeableState,
                anchors = mapOf(
                    0f to 0,
                    -dipToPixels(LocalContext.current, 100f) to 1,
                    dipToPixels(LocalContext.current, 0f) to 0,
                ),
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .background(screenBackground)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = {
                    scope.launch {
                        swipeableState.animateTo(0, tween(600, 0))
                    }
                    onEditClick.invoke(
                        doorModel
                    )
                }
            ) {
                Image(painterResource(id = R.drawable.edit), contentDescription = null)
            }
            IconButton(
                onClick = {
                    scope.launch {
                        swipeableState.animateTo(0, tween(600, 0))
                    }
                }
            ) {
                Image(painterResource(id = R.drawable.ic_star), contentDescription = null)
            }

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(swipeableState.offset.value.roundToInt(), 0)
                },
            backgroundColor = cardBackgroundColor,
            elevation = 2.dp,
            shape = RoundedCornerShape(cardRadius)
        ) {
            Column {
                if (doorModel.imageUrl != null) {
                    Box(
                        modifier = Modifier
                            .height(207.dp)
                    ) {
                        AsyncImage(
                            model = doorModel.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0x66000000)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.play_button),
                                contentDescription = null
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.rec),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(4.dp)
                                .align(Alignment.TopStart)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = doorModel.name,
                        color = toolBarTextColor,
                        fontSize = tabTextSize,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
                    )
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_lock),
                            contentDescription = null,
                            tint = blue
                        )
                    }
                }
            }

        }
    }
}



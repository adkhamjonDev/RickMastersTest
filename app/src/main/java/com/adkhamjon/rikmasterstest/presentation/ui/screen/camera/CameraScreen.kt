package com.adkhamjon.rikmasterstest.presentation.ui.screen.camera

import android.graphics.drawable.GradientDrawable
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.adkhamjon.rikmasterstest.R
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.presentation.ui.theme.blue
import com.adkhamjon.rikmasterstest.presentation.ui.theme.cardBackgroundColor
import com.adkhamjon.rikmasterstest.presentation.ui.theme.cardRadius
import com.adkhamjon.rikmasterstest.presentation.ui.theme.screenBackground
import com.adkhamjon.rikmasterstest.presentation.ui.theme.tabTextSize
import com.adkhamjon.rikmasterstest.presentation.ui.theme.toolBarTextColor
import com.adkhamjon.rikmasterstest.presentation.utils.dipToPixels
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun CameraScreen(
    viewModel: CameraScreenViewModel = hiltViewModel()
) {
    val cameraListState = viewModel.camera.collectAsState().value

    val cameraList = remember {
        mutableStateListOf<CameraModel>()
    }
    var progress by remember {
        mutableStateOf(true)
    }
    if (cameraListState.isLoading) {
        progress = true
    }
    if (cameraListState.error.isNotEmpty()) {
        Toast.makeText(LocalContext.current, cameraListState.error, Toast.LENGTH_SHORT).show()
    }
    if (cameraListState.data != null) {
        progress = false
        cameraList.clear()
        cameraList.addAll(cameraListState.data)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackground),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(cameraList) {
                CameraItem(it)
            }
        }
        if (progress) {
            CircularProgressIndicator(color = blue)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CameraItem(
    cameraModel: CameraModel
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
        IconButton(
            onClick = {
                scope.launch {
                    swipeableState.animateTo(0, tween(600, 0))
                }
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 20.dp)
        ) {
            Image(painterResource(id = R.drawable.ic_star), contentDescription = null)
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
                AsyncImage(
                    model = cameraModel.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(207.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = cameraModel.name,
                    color = toolBarTextColor,
                    fontSize = tabTextSize,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(vertical = 30.dp, horizontal = 20.dp)
                )
            }
        }
    }
}


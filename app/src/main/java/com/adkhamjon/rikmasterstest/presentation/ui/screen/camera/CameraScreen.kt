package com.adkhamjon.rikmasterstest.presentation.ui.screen.camera

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
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

@Composable
fun CameraItem(
    cameraModel: CameraModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
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

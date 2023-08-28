package com.adkhamjon.rikmasterstest.data.mapper

import com.adkhamjon.rikmasterstest.data.remote.dto.cameras.CameraDto
import com.adkhamjon.rikmasterstest.data.remote.dto.doors.DoorResponseDto
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.domain.model.DoorModel

fun CameraDto.toModel() = CameraModel(
    id = id,
    name = name,
    imageUrl = snapshot
)

fun DoorResponseDto.toModel() = DoorModel(
    id = id,
    name = name,
    imageUrl = snapshot
)
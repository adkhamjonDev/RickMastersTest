package com.adkhamjon.rikmasterstest.data.mapper

import com.adkhamjon.rikmasterstest.data.local.entity.CameraEntity
import com.adkhamjon.rikmasterstest.data.local.entity.DoorEntity
import com.adkhamjon.rikmasterstest.data.remote.dto.cameras.CameraDto
import com.adkhamjon.rikmasterstest.data.remote.dto.doors.DoorResponseDto
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.domain.model.DoorModel

fun CameraDto.toEntity() = CameraEntity(
    id = id,
    name = name,
    imageUrl = snapshot,
    favorite = favorites
)

fun CameraEntity.toModel() = CameraModel(
    id = id,
    name = name,
    imageUrl = imageUrl,
    favorite = favorite
)

fun DoorResponseDto.toEntity() = DoorEntity(
    id = id,
    name = name,
    imageUrl = snapshot
)

fun DoorEntity.toModel() = DoorModel(
    id = id,
    name = name,
    imageUrl = imageUrl
)
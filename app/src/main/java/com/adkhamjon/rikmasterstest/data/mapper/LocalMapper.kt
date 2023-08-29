package com.adkhamjon.rikmasterstest.data.mapper

import com.adkhamjon.rikmasterstest.data.local.entity.CameraEntity
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
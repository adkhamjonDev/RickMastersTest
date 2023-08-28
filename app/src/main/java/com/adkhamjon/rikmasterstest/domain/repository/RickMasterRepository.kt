package com.adkhamjon.rikmasterstest.domain.repository

import com.adkhamjon.rikmasterstest.domain.Resource
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.domain.model.DoorModel
import kotlinx.coroutines.flow.Flow

interface RickMasterRepository {

    suspend fun getCameras(): Flow<Resource<List<CameraModel>>>

    suspend fun getDoors(): Flow<Resource<List<DoorModel>>>
}
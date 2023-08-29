package com.adkhamjon.rikmasterstest.domain.repository

import com.adkhamjon.rikmasterstest.data.remote.FLOW_RESOURCE
import com.adkhamjon.rikmasterstest.domain.Resource
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.domain.model.DoorModel
import kotlinx.coroutines.flow.Flow

interface RickMasterRepository {

    suspend fun getCameras(): FLOW_RESOURCE<List<CameraModel>>

    suspend fun getDoors(): FLOW_RESOURCE<List<DoorModel>>

}
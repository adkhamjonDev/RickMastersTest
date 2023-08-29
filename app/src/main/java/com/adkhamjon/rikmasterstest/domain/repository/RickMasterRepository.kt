package com.adkhamjon.rikmasterstest.domain.repository

import com.adkhamjon.rikmasterstest.data.common.FLOW_RESOURCE
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.domain.model.DoorModel

interface RickMasterRepository {

    suspend fun getCameras(): FLOW_RESOURCE<List<CameraModel>>

    suspend fun getDoors(): FLOW_RESOURCE<List<DoorModel>>

    suspend fun updateName(id: Int, name: String)

}
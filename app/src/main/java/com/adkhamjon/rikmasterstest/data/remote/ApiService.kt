package com.adkhamjon.rikmasterstest.data.remote

import com.adkhamjon.rikmasterstest.data.remote.dto.MainResponse
import com.adkhamjon.rikmasterstest.data.remote.dto.cameras.CameraResponseDto
import com.adkhamjon.rikmasterstest.data.remote.dto.doors.DoorResponseDto

interface ApiService {

    suspend fun getCameras(): MainResponse<CameraResponseDto>

    suspend fun getDoors(): MainResponse<List<DoorResponseDto>>
}
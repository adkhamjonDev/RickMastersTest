package com.adkhamjon.rikmasterstest.data.remote

import com.adkhamjon.rikmasterstest.data.remote.dto.MainResponse
import com.adkhamjon.rikmasterstest.data.remote.dto.cameras.CameraResponseDto
import com.adkhamjon.rikmasterstest.data.remote.dto.doors.DoorResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val httpClient: HttpClient
) : ApiService {
    override suspend fun getCameras(): MainResponse<CameraResponseDto> {
        return httpClient.get {
            url(HttpRoutes.GET_CAMERAS)
        }
    }

    override suspend fun getDoors(): MainResponse<List<DoorResponseDto>> {
        return httpClient.get {
            url(HttpRoutes.GET_DOORS)
        }
    }

}


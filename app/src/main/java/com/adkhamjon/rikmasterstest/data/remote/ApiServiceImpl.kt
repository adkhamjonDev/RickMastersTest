package com.adkhamjon.rikmasterstest.data.remote

import android.util.Log
import com.adkhamjon.rikmasterstest.data.remote.dto.MainResponse
import com.adkhamjon.rikmasterstest.data.remote.dto.cameras.CameraResponseDto
import com.adkhamjon.rikmasterstest.data.remote.dto.doors.DoorResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.response
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val httpClient: HttpClient
) : ApiService {
    override suspend fun getCameras(): MainResponse<CameraResponseDto> {
        return try {
            httpClient.get {
                url(HttpRoutes.GET_CAMERAS)
            }
        } catch (e: RedirectResponseException) {
            MainResponse(CameraResponseDto(emptyList(), emptyList()), false)
        } catch (e: Exception) {
            MainResponse(CameraResponseDto(emptyList(), emptyList()), false)
        }

    }

    override suspend fun getDoors(): MainResponse<List<DoorResponseDto>> {
        return try {
            httpClient.get {
                url(HttpRoutes.GET_DOORS)
            }
        } catch (e: RedirectResponseException) {
            Log.d("TTT", "getDoors: ${e.response.response}")
            MainResponse(emptyList(), false)
        } catch (e: Exception) {
            Log.d("TTT", "getDoors: ${e.message}")
            MainResponse(emptyList(), false)
        }
    }
}
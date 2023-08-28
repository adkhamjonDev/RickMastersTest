package com.adkhamjon.rikmasterstest.domain.use_case.camera

import com.adkhamjon.rikmasterstest.domain.repository.RickMasterRepository
import javax.inject.Inject

class GetCamerasUseCase @Inject constructor(
    private val repository: RickMasterRepository
) {
    suspend operator fun invoke() = repository.getCameras()
}
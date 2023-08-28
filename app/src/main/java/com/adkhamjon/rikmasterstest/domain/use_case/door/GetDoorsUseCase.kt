package com.adkhamjon.rikmasterstest.domain.use_case.door

import com.adkhamjon.rikmasterstest.domain.repository.RickMasterRepository
import javax.inject.Inject

class GetDoorsUseCase @Inject constructor(
    private val repository: RickMasterRepository
) {
    suspend operator fun invoke() = repository.getDoors()
}
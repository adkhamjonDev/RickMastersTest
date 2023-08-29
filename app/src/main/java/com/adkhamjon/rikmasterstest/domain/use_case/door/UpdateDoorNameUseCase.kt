package com.adkhamjon.rikmasterstest.domain.use_case.door

import com.adkhamjon.rikmasterstest.domain.repository.RickMasterRepository
import javax.inject.Inject

class UpdateDoorNameUseCase @Inject constructor(
    private val repository: RickMasterRepository
) {
    suspend operator fun invoke(id: Int, name: String) = repository.updateName(id, name)
}
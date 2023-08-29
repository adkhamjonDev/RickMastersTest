package com.adkhamjon.rikmasterstest.data.repository

import android.util.Log
import com.adkhamjon.rikmasterstest.data.ResultHandler
import com.adkhamjon.rikmasterstest.data.local.entity.CameraEntity
import com.adkhamjon.rikmasterstest.data.mapper.toEntity
import com.adkhamjon.rikmasterstest.data.mapper.toModel
import com.adkhamjon.rikmasterstest.data.remote.ApiService
import com.adkhamjon.rikmasterstest.domain.Resource
import com.adkhamjon.rikmasterstest.domain.model.CameraModel
import com.adkhamjon.rikmasterstest.domain.model.DoorModel
import com.adkhamjon.rikmasterstest.domain.repository.RickMasterRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RickMasterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val realm: Realm
) : RickMasterRepository, ResultHandler() {

    override suspend fun getCameras() = loadResult(
        {
            apiService.getCameras()
        }, { data, flow ->
            try {

                realm.write {
                    val cameras: RealmResults<CameraEntity> = this.query<CameraEntity>().find()
                    delete(cameras)
                }
                data.cameras.map { camera ->
                    realm.write {
                        copyToRealm(
                            camera.toEntity()
                        )
                    }
                }
                val list = data.cameras.map {
                    it.toModel()
                }
                flow.emit(Resource.Success(list))
            } catch (e: Exception) {
                flow.emit(Resource.Error("try catch"))
            }
        }
    )

    override suspend fun getDoors() = loadResult(
        {
            apiService.getDoors()
        },
        { data, flow ->
            try {
                val list = data.map {
                    it.toModel()
                }
                flow.emit(Resource.Success(list))
            } catch (e: Exception) {
                flow.emit(Resource.Error("try catch"))
            }
        }
    )

    override suspend fun saveCameras(cameraModel: CameraModel) {
        TODO("Not yet implemented")
    }

    override suspend fun saveDoors(doorModel: DoorModel) {
        TODO("Not yet implemented")
    }

}
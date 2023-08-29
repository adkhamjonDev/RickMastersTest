package com.adkhamjon.rikmasterstest.data.repository

import android.util.Log
import com.adkhamjon.rikmasterstest.data.ResultHandler
import com.adkhamjon.rikmasterstest.data.local.entity.CameraEntity
import com.adkhamjon.rikmasterstest.data.local.entity.DoorEntity
import com.adkhamjon.rikmasterstest.data.mapper.toEntity
import com.adkhamjon.rikmasterstest.data.mapper.toModel
import com.adkhamjon.rikmasterstest.data.remote.ApiService
import com.adkhamjon.rikmasterstest.data.remote.FLOW_RESOURCE
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

    override suspend fun getCameras(): FLOW_RESOURCE<List<CameraModel>> = loadResult(
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
        },
        { flow ->
            val cameras: RealmResults<CameraEntity> = realm.query<CameraEntity>().find()
            flow.emit(
                Resource.Success(
                    cameras.map {
                        it.toModel()
                    }
                )
            )
        }

    )

    override suspend fun getDoors(): FLOW_RESOURCE<List<DoorModel>> = loadResult(
        {
            apiService.getDoors()
        },
        { data, flow ->
            try {
                realm.write {
                    val cameras: RealmResults<DoorEntity> = this.query<DoorEntity>().find()
                    delete(cameras)
                }
                data.map { door ->
                    realm.write {
                        copyToRealm(
                            door.toEntity()
                        )
                    }
                }
                val list = data.map {
                    it.toModel()
                }
                flow.emit(Resource.Success(list))
            } catch (e: Exception) {
                flow.emit(Resource.Error("try catch"))
            }
        }, { flow ->
            val doors: RealmResults<DoorEntity> = realm.query<DoorEntity>().find()
            flow.emit(
                Resource.Success(
                    doors.map {
                        it.toModel()
                    }
                )
            )
        }
    )

}
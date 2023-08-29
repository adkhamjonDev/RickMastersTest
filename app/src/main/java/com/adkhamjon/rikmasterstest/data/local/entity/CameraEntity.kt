package com.adkhamjon.rikmasterstest.data.local.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey

class CameraEntity : RealmObject {
    @PrimaryKey
    var id: Int = 0

    @Ignore
    var name: String = ""
    var imageUrl: String = ""
    var favorite: Boolean = false

    constructor(id: Int, name: String, imageUrl: String, favorite: Boolean) {
        this.id = id
        this.name = name
        this.imageUrl = imageUrl
        this.favorite = favorite
    }
}
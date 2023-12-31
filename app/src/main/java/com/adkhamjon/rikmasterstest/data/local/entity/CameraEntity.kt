package com.adkhamjon.rikmasterstest.data.local.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey

class CameraEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var imageUrl: String,
    var favorite: Boolean
) : RealmObject {
    constructor() : this(
        id = 0,
        name = "",
        imageUrl = "",
        favorite = false
    )
}
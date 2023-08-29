package com.adkhamjon.rikmasterstest.data.local.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey

class DoorEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var imageUrl: String? = null
) : RealmObject {
    constructor() : this(
        id = 0,
        name = "",
        imageUrl = null
    )
}
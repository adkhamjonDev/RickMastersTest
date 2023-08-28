package com.adkhamjon.rikmasterstest.data.remote

object HttpRoutes {
    private const val BASE_URL = "http://cars.cprogroup.ru/api/rubetek"
    const val GET_CAMERAS = "${BASE_URL}/cameras"
    const val GET_DOORS = "${BASE_URL}/doors"
}
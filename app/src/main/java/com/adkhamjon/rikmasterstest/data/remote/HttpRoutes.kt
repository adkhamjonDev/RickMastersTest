package com.adkhamjon.rikmasterstest.data.remote

import com.adkhamjon.rikmasterstest.R
import com.adkhamjon.rikmasterstest.presentation.utils.getString

object HttpRoutes {
    private val BASE_URL = getString(R.string.base_url)
    val GET_CAMERAS = "${BASE_URL}/cameras"
    val GET_DOORS = "${BASE_URL}/doors"
}
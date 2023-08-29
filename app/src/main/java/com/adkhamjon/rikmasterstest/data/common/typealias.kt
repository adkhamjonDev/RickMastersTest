package com.adkhamjon.rikmasterstest.data.common

import com.adkhamjon.rikmasterstest.domain.Resource
import kotlinx.coroutines.flow.Flow


typealias FLOW_RESOURCE<T> = Flow<Resource<T>>
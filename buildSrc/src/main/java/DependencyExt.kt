import Params.ANDROID_TEST_IMPLEMENTATION
import Params.ANNOTATION_PROCESSOR
import Params.DEBUG_IMPLEMENTATION
import Params.IMPLEMENTATION
import Params.KAPT
import Params.RELEASE_IMPLEMENTATION
import Params.TEST_IMPLEMENTATION
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.hilt() {
    add(IMPLEMENTATION, Dependencies.Hilt.hilt_android)
    add(KAPT, Dependencies.Hilt.hilt_android_compiler)
    add(KAPT, Dependencies.Hilt.hilt_compiler)
    add(IMPLEMENTATION, Dependencies.Hilt.hilt_navigation)
}

fun DependencyHandlerScope.coroutines() {
    add(IMPLEMENTATION, Dependencies.Coroutine.coroutine_android)
    add(IMPLEMENTATION, Dependencies.Coroutine.coroutine_core)
}

fun DependencyHandlerScope.ktor(){
    add(IMPLEMENTATION, Dependencies.Ktor.ktor_android)
    add(IMPLEMENTATION, Dependencies.Ktor.ktor_serialization)
    add(IMPLEMENTATION, Dependencies.Ktor.ktor_logging)
}
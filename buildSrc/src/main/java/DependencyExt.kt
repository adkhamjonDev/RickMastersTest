import Params.ANDROID_TEST_IMPLEMENTATION
import Params.ANNOTATION_PROCESSOR
import Params.DEBUG_IMPLEMENTATION
import Params.IMPLEMENTATION
import Params.KAPT
import Params.RELEASE_IMPLEMENTATION
import Params.TEST_IMPLEMENTATION
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope


fun DependencyHandlerScope.chuckInterceptor() {
    add(DEBUG_IMPLEMENTATION, Dependencies.ChuckInterceptor.chuck)
    add(RELEASE_IMPLEMENTATION, Dependencies.ChuckInterceptor.chuck_no_op)
}

fun DependencyHandlerScope.hilt() {
    add(IMPLEMENTATION, Dependencies.Hilt.hilt_android)
    add(KAPT, Dependencies.Hilt.hilt_android_compiler)
    add(KAPT, Dependencies.Hilt.hilt_compiler)
    add(IMPLEMENTATION, Dependencies.Hilt.hilt_navigation)
}
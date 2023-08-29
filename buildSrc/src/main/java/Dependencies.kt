import java.util.*

object Dependencies {

    object Core {
        const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"

        const val kotlin_boom = "org.jetbrains.kotlin:kotlin-bom:${Versions.kotlin_boom}"

        const val lifecycle_runtime_ktx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"

        const val compose_boom = "androidx.compose:compose-bom:${Versions.compose_boom}"

        const val activity_compose =
            "androidx.activity:activity-compose:${Versions.activity_compose}"

        const val compose_ui = "androidx.compose.ui:ui:${Versions.compose_version}"

        const val compose_ui_tooling_preview =
            "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
        const val compose_ui_tooling =
            "androidx.compose.ui:ui-tooling:${Versions.compose_version}"

        const val compose_material =
            "androidx.compose.material:material:${Versions.compose_version}"

    }

    object Test {
        const val junit = "junit:junit:${Versions.junit_version}"
        const val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit_version}"
        const val espresso_core =
            "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"

    }

    object ComposeTest {
        const val junit = "androidx.compose.ui:ui-test-junit4:${Versions.compose_version_test}"
        const val ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version_test}"
        const val test_manifest =
            "androidx.compose.ui:ui-test-manifest:${Versions.compose_version_test}"
    }

    object ChuckInterceptor {
        const val chuck = "com.github.chuckerteam.chucker:library:${Versions.chuck_version}"
        const val chuck_no_op =
            "com.github.chuckerteam.chucker:library-no-op:${Versions.chuck_version}}"
    }

    object Coroutine {
        const val coroutine_android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
        const val coroutine_core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    }

    object Gson {
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object Hilt {
        const val hilt_android = "com.google.dagger:hilt-android:2.44.2"
        const val hilt_android_compiler =
            "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
        const val hilt_compiler = "androidx.hilt:hilt-compiler:${Versions.hilt_compiler}"
        const val hilt_navigation =
            "androidx.hilt:hilt-navigation-compose:${Versions.hilt_compiler}"

    }
}
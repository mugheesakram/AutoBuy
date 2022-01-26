//    Kotlin
object KotlinDependencies {
    const val KOTLIN_REFLECTION =
        "org.jetbrains.kotlin:kotlin-reflect:${KotlinVersions.STANDARD_LIBRARY}"
    const val KOTLIN_STD_LIB =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${KotlinVersions.STANDARD_LIBRARY}"
    // kotlin coroutine
    const val COROUTINE_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${KotlinVersions.COROUTINE_VERSION}"
    const val COROUTINE_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${KotlinVersions.COROUTINE_VERSION}"
    const val Collection_KTX =
        "androidx.collection:collection-ktx:${KotlinVersions.Collection_KTX}"
}

// Retrofit2 & Networking
object NetworkDependencies {

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${NetworkVersions.RETROFIT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${NetworkVersions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${NetworkVersions.OKHTTP}"
    const val RETROFIT_CONVERTOR_GSON =
        "com.squareup.retrofit2:converter-gson:${NetworkVersions.RETROFIT}"
    const val GSON = "com.google.code.gson:gson:${NetworkVersions.GSON}"

    //Glide Image Loading
    const val GLIDE = "com.github.bumptech.glide:glide:${NetworkVersions.GLIDE}"
}

//LifeCycle_KTX
object LifeCycleKtxDependencies {
    const val CORE_KTX = "androidx.core:core-ktx:${LifeCycle_KTX.CORE_KTX}"
    const val LIFECYCL_EXTENSIONS =
        "androidx.lifecycle:lifecycle-extensions:${LifeCycle_KTX.LIFECYCL_EEXTENSIONS}"
    const val VIEW_MODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LifeCycle_KTX.LIFECYCLE}"
    const val VIEW_MODEL_SAVE_STATE_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${LifeCycle_KTX.LIFECYCLE}"
    const val LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${LifeCycle_KTX.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime:${LifeCycle_KTX.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${LifeCycle_KTX.LIFECYCLE}"
    const val LIFECYCLE_COMMON_JAVA =
        "androidx.lifecycle:lifecycle-common-java8:${LifeCycle_KTX.LIFECYCLE}"
    const val REACTIVE_STREAMS =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:${LifeCycle_KTX.LIFECYCLE}"
}

// Androidx Architecture
// Androidx

object AndroidxDependencies {
    const val APPCOMPAT = "androidx.appcompat:appcompat:${AndroidXVersions.APPCOMPAT}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${AndroidXVersions.ACTIVITY_KTX}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${AndroidXVersions.CONSTRAINT_LAYOUT}"
    const val CONSTRAINT_LAYOUT_SOLVER =
        "androidx.constraintlayout:constraintlayout-solver:${AndroidXVersions.CONSTRAINT_LAYOUT}"
    const val RECYCLERVIEW =
        "androidx.recyclerview:recyclerview:${AndroidXVersions.RECYCLERVIEW}"
    const val MATERIAL = "com.google.android.material:material:${AndroidXVersions.MATERIAL}"
    const val SWIPE_REFRESH = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val PAGING = "androidx.paging:paging-runtime:${AndroidXVersions.PAGING}"
}

object HiltDaggerDependencies {
    // Hilt Dagger DI
    const val DAGGER_HILT = "com.google.dagger:hilt-android:${HiltDaggerVersion.HILT_DI}"
    const val DAGGER_COMPILER =
        "com.google.dagger:hilt-android-compiler:${HiltDaggerVersion.HILT_DI}"
    const val HILT_FRAGMENT = "androidx.hilt:hilt-navigation-fragment:${HiltDaggerVersion.HILT_VM}"
}

object TestDependencies {
    const val JUNIT = "junit:junit:${TestDependenciesVersions.JUNIT4}"
    const val MOCKK = "io.mockk:mockk:${TestDependenciesVersions.MOCKK}"
    const val MOCKK_ANDROID = "io.mockk:mockk-android:${TestDependenciesVersions.MOCKK}"
    const val TESTRUNNER = "androidx.test:runner:${TestDependenciesVersions.TESTRUNNER}"
    const val TESTRULES = "androidx.test:rules:${TestDependenciesVersions.TESTRULES}"
    const val TESTEXTENSIONS = "androidx.test.ext:junit:${TestDependenciesVersions.TESTEXTENSIONS}"
    const val HILTTESTING =
        "com.google.dagger:hilt-android-testing:${TestDependenciesVersions.HILTTESTING}"
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestDependenciesVersions.COROUTINES}"
    const val ANDROIDX_ARCH_CORE =
        "androidx.arch.core:core-testing:${TestDependenciesVersions.ANDROIDX_ARCH_CORE}"
}

object RoomDependencies {
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${AndroidXVersions.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${AndroidXVersions.ROOM_VERSION}"
    const val ROOM_KTX = "androidx.room:room-ktx:${AndroidXVersions.ROOM_VERSION}"
}

object NavigationDependencies {
    //    Navigation KTX
    const val NAVIGATION_RUNTIME =
        "androidx.navigation:navigation-runtime-ktx:${AndroidXVersions.NAVIGATION}"
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${AndroidXVersions.NAVIGATION}"
    const val NAVIGATION_UI_KTX =
        "androidx.navigation:navigation-ui-ktx:${AndroidXVersions.NAVIGATION}"
}



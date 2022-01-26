import org.gradle.api.artifacts.dsl.DependencyHandler
import java.util.*

object DependenciesManager {
    val kotlinImplementation = arrayListOf<String>().apply {
        add(KotlinDependencies.KOTLIN_REFLECTION)
        add(KotlinDependencies.KOTLIN_STD_LIB)
        add(KotlinDependencies.COROUTINE_CORE)
        add(KotlinDependencies.COROUTINE_ANDROID)
        add(KotlinDependencies.Collection_KTX)
    }

    val networkImplementation = arrayListOf<String>().apply {
        add(NetworkDependencies.RETROFIT)
        add(NetworkDependencies.OKHTTP)
        add(NetworkDependencies.OKHTTP_LOGGING_INTERCEPTOR)
        add(NetworkDependencies.RETROFIT_CONVERTOR_GSON)
        add(NetworkDependencies.GSON)
        add(NetworkDependencies.GLIDE)
    }
    val lifeCycleKtxImplementation = arrayListOf<String>().apply {
        add(LifeCycleKtxDependencies.CORE_KTX)
        add(LifeCycleKtxDependencies.LIFECYCL_EXTENSIONS)
        add(LifeCycleKtxDependencies.VIEW_MODEL_KTX)
        add(LifeCycleKtxDependencies.VIEW_MODEL_SAVE_STATE_KTX)
        add(LifeCycleKtxDependencies.LIVEDATA_KTX)
        add(LifeCycleKtxDependencies.LIFECYCLE_RUNTIME)
        add(LifeCycleKtxDependencies.LIFECYCLE_RUNTIME_KTX)
        add(LifeCycleKtxDependencies.LIFECYCLE_COMMON_JAVA)
        add(LifeCycleKtxDependencies.REACTIVE_STREAMS)
    }
    val androidxImplementation = arrayListOf<String>().apply {
        add(AndroidxDependencies.APPCOMPAT)
        add(AndroidxDependencies.ACTIVITY_KTX)
        add(AndroidxDependencies.CONSTRAINT_LAYOUT)
        add(AndroidxDependencies.CONSTRAINT_LAYOUT_SOLVER)
        add(AndroidxDependencies.RECYCLERVIEW)
        add(AndroidxDependencies.MATERIAL)
        add(AndroidxDependencies.SWIPE_REFRESH)
        add(AndroidxDependencies.PAGING)
    }

    val hiltImplementation = arrayListOf<String>().apply {
        add(HiltDaggerDependencies.DAGGER_HILT)
        add(HiltDaggerDependencies.HILT_FRAGMENT)
    }
    val hiltKapt = arrayListOf<String>().apply {
        add(HiltDaggerDependencies.DAGGER_COMPILER)
    }
    val testingImplementation = arrayListOf<String>().apply {
        add(TestDependencies.JUNIT)
        add(TestDependencies.MOCKK)
        add(TestDependencies.TESTEXTENSIONS)
        add(TestDependencies.HILTTESTING)
        add(TestDependencies.COROUTINES)
        add(TestDependencies.ANDROIDX_ARCH_CORE)
    }
    val androidTestImplementation = arrayListOf<String>().apply {
        add(TestDependencies.MOCKK_ANDROID)
        add(TestDependencies.TESTEXTENSIONS)
        add(TestDependencies.TESTRUNNER)
        add(TestDependencies.TESTRULES)
        add(TestDependencies.HILTTESTING)
    }

    val roomImplementation = arrayListOf<String>().apply {
        add(RoomDependencies.ROOM_RUNTIME)
        add(RoomDependencies.ROOM_KTX)
    }

    val navigationImplementation = arrayListOf<String>().apply {
        add(NavigationDependencies.NAVIGATION_RUNTIME)
        add(NavigationDependencies.NAVIGATION_FRAGMENT_KTX)
        add(NavigationDependencies.NAVIGATION_UI_KTX)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
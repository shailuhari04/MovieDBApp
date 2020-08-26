object Versions {
    const val coroutines = "1.3.8"
    const val appcompat = "1.2.0"
    const val fragment = "1.2.5"
    const val constraintlayout = "2.0.0"
    const val material = "1.2.0"
    const val lifecycle = "2.2.0"
    const val arch = "2.1.0"
    const val retrofit = "2.9.0"
    const val okhttp = "4.8.1"
    const val koin = "2.1.6"
    const val glide = "4.11.0"
    const val junit = "4.13"
    const val mockk = "1.10.0"
    const val kotest = "4.1.2"
    const val androidxTest = "1.2.0"
    const val robolectric = "4.3.1"
    const val paging = "2.1.2"
    const val gson = "2.8.6"
}

object BuildVersions {
    const val agp = "4.0.1"
    const val kotlin = "1.4.0"
}

object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinAndroidExtensions = "org.jetbrains.kotlin.android.extensions"
    const val kotlinKapt = "org.jetbrains.kotlin.kapt"
}

object Libs {
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val kotestAssertions = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
    const val androidxTest = "androidx.test:core:${Versions.androidxTest}"
    const val archTest = "androidx.arch.core:core-testing:${Versions.arch}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}
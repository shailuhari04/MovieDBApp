plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

android {
    compileSdkVersion(Sdk.compileVersion)
    defaultConfig {
        minSdkVersion(Sdk.minVersion)
        targetSdkVersion(Sdk.targetVersion)

        applicationId = App.id
        versionCode = App.versionCode
        versionName = App.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName(AppBuildType.release) {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create(AppBuildType.staging) {
            isDebuggable = true
            isMinifyEnabled = true
            isShrinkResources = true
        }

        getByName(AppBuildType.debug) {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
    buildFeatures {
        dataBinding = true
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    productFlavors {
        create(AppFlavors.dev) {
            versionCode = App.versionCode
            versionName = App.versionName
            applicationIdSuffix = App.suffixDev
            resValue("string", "app_name", "MovieDB Dev App")
            buildConfigField("boolean", "MOCK_DATA", "false")
        }
        create(AppFlavors.mock) {
            versionCode = App.versionCode
            versionName = App.versionName
            applicationIdSuffix = App.suffixMock
            resValue("string", "app_name", "MovieDB Mock App")
            buildConfigField("boolean", "MOCK_DATA", "true")
        }
        create(AppFlavors.prod) {
            versionCode = App.versionCode
            versionName = App.versionName
            resValue("string", "app_name", "MovieDB App")
            buildConfigField("boolean", "MOCK_DATA", "false")
        }
    }

    flavorDimensions("default")

    applicationVariants.all {
        buildConfigField("String", "API_KEY", "\"2cdf3a5c7cf412421485f89ace91e373\"")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
        buildConfigField("String", "SMALL_IMAGE_URL", "\"https://image.tmdb.org/t/p/w200\"")
        buildConfigField("String", "LARGE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w500\"")
        buildConfigField("String", "ORIGINAL_IMAGE_URL", "\"https://image.tmdb.org/t/p/original\"")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // UI and Appcompat
    implementation(Libs.appcompat)
    implementation(Libs.fragmentKtx)
    implementation(Libs.constraintlayout)
    implementation(Libs.material)

    // Coroutines
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

    // ViewModel and LiveData
    implementation(Libs.viewmodelKtx)
    implementation(Libs.livedataKtx)
    implementation(Libs.lifecycleRuntimeKtx)

    // Koin
    implementation(Libs.koinAndroid)
    implementation(Libs.koinViewmodel)

    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofitConverterMoshi)
    implementation(Libs.loggingInterceptor)

    //Paging
    implementation(Libs.paging)

    // Glide
    implementation(Libs.glide)
    kapt(Libs.glideCompiler)

    //Unit Testing
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.kotestAssertions)
    testImplementation(TestLibs.androidxTest)
    testImplementation(TestLibs.archTest)
    testImplementation(TestLibs.coroutinesTest)
    testImplementation(TestLibs.robolectric)
}
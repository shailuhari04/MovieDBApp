// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(BuildPlugins.androidApplication) version BuildVersions.agp apply true
    id(BuildPlugins.kotlinAndroid) version BuildVersions.kotlin apply true
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


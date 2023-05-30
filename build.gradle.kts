// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.0-alpha08" apply false
    id("com.android.library") version "8.0.0-alpha08" apply false
    kotlin("android") version "1.8.0" apply false
    //ksp plugin
    id("com.google.devtools.ksp") version "1.8.0-1.0.8" apply false

    //导航安全传递参数(for kotlin)
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0" apply false

    //hilt plugin
    id("com.google.dagger.hilt.android") version "2.44" apply false
    //datastore pro
    id("com.google.protobuf") version "0.8.17" apply false
}
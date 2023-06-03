import com.google.protobuf.gradle.*

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp")
    //导航参数插件
    id("androidx.navigation.safeargs.kotlin")
    //kotlin序列化
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization")
    //应用hilt插件
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    //protobuf for datastore
    id("com.google.protobuf")
}

android {
    namespace = "com.example.jetpack_demos"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.jetpack_demos"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        //生成字节码目标版本
        jvmTarget = "17"
        //kotlin 语言版本
        //languageVersion="1.8"
    }
    buildFeatures {
        //视图绑定
        viewBinding = true
        //数据绑定
        dataBinding = true
    }

    sourceSets {
        getByName("debug") {
            res.srcDirs("$projectDir/roomSchema")
        }
    }
}

//添加处理器参数
ksp {
    arg("room.schemaLocation", File(rootDir, "roomSchema").absolutePath)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.test:monitor:1.5.0")
    implementation("androidx.test.ext:junit-ktx:1.1.3")
    implementation(project(mapOf("path" to ":demo-module-nav")))
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.preference:preference:1.2.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation(project(mapOf("path" to ":login-module-for-hilt-multimodule")))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    val work_version = "2.8.0"
    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$work_version")
    implementation("androidx.work:work-multiprocess:2.7.1")

    val room_version = "2.5.0"
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
    //测试
    testImplementation("androidx.room:room-testing:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")
    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")
    //viewmodel
    val lifecycle_version = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    //startup
    implementation("androidx.startup:startup-runtime:1.1.1")
    //Google Shortcuts Integration Library
//    implementation("androidx.core:core-google-shortcuts:1.0.0")
    //启动屏
    implementation("androidx.core:core-splashscreen:1.0.0")
    //导航库
    val nav_version = "2.5.3"
    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    //predictive back gesture，Android13.0开始支持预测返回手势
    implementation("androidx.activity:activity:1.6.0-alpha05")
    //Androidx Fragment依赖
    val fragment_version = "1.5.7"
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    //Glide图片加载库
    implementation("com.github.bumptech.glide:glide:4.15.1")

    //hilt依赖
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

//    implementation 'com.google.dagger:dagger:2.44'
//    kapt 'com.google.dagger:dagger-compiler:2.44'
    //drag and drop
    implementation("androidx.draganddrop:draganddrop:1.0.0-alpha02")
    //datastore
    //sharedpreferences
    implementation("androidx.datastore:datastore-preferences:1.1.0-alpha03")
    //datastore for proto
    implementation("androidx.datastore:datastore:1.1.0-alpha03")
    // Starting from Protobuf 3.8.0, use the lite runtime library
    implementation("com.google.protobuf:protobuf-javalite:3.18.0")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

protobuf {
    protoc {
        // find latest version number here:
        // https://mvnrepository.com/artifact/com.google.protobuf/protoc
        artifact = "com.google.protobuf:protoc:3.18.0"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}


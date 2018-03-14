/*
 * Copyright 2017 Vladimir Jovanovic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object Versions {
    val support_lib = "27.0.2"
    val gson = "2.8.2"
    val retrofit2 = "2.3.0"
    val rxandroid2 = "2.0.1"
    val rxjava2 = "2.1.9"
    val logging_interceptor = "3.9.0"
    val glide = "4.6.1"
    val kotlin = "1.2.21"
    val dagger2 = "2.15"

    val junit = "4.12"
    val mockito_core = "2.11.0"
    val mockito_kotlin = "1.5.0"
    val truth = "0.39"
    val robolectric = "3.6.1"

    val gradle_android = "3.0.1"

    val jacoco = "0.8.0"

    val min_sdk = 15
    val target_sdk = 27
    val compile_sdk = 27
    val build_tools = "27.0.3"

    val version_code = 100
    val version_name = "1.0.0"
}

object Deps {
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    val retrofit2_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
    val retrofit2_rxjava2_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit2}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support_lib}"
    val recyclerview_v7 = "com.android.support:recyclerview-v7:${Versions.support_lib}"
    val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
    val rxandroid2 = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid2}"
    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val dagger2 = "com.google.dagger:dagger:${Versions.dagger2}"
    val dagger2_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger2}"

    val junit = "junit:junit:${Versions.junit}"
    val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    val mockito_kotlin = "com.nhaarman:mockito-kotlin:${Versions.mockito_kotlin}"
    val truth = "com.google.truth:truth:${Versions.truth}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle_android}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

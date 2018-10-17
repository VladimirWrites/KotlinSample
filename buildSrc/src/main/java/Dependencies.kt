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
    val support_lib = "28.0.0"
    val gson = "2.8.5"
    val retrofit2 = "2.4.0"
    val logging_interceptor = "3.9.0"
    val glide = "4.8.0"
    val kotlin = "1.2.71"

    val koin = "1.0.1"

    val junit = "4.12"
    val mockito_core = "2.23.0"
    val mockito_kotlin = "2.0.0-RC3"
    val truth = "0.42"
    val robolectric = "3.8"

    val gradle_android = "3.2.1"

    val jacoco = "0.8.2"

    val min_sdk = 21
    val target_sdk = 28
    val compile_sdk = 28
    val build_tools = "28.0.3"

    val version_code = 100
    val version_name = "1.0.0"
    val retrofit2_coroutines_adapter = "1.0.0"
}

object Deps {
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    val retrofit2_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
    val retrofit2_coroutines_adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:${Versions.retrofit2_coroutines_adapter}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support_lib}"
    val recyclerview_v7 = "com.android.support:recyclerview-v7:${Versions.support_lib}"
    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    val junit = "junit:junit:${Versions.junit}"
    val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    val truth = "com.google.truth:truth:${Versions.truth}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    val kotlin_test = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"

    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle_android}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    val koin = "org.koin:koin-android:${Versions.koin}"
}

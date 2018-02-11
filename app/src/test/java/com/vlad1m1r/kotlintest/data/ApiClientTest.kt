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

package com.vlad1m1r.kotlintest.data

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.mock
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

class ApiClientTest {

    private val apiClient = ApiClient()

    @Test
    fun constructor() {
        assertThat(apiClient.client).isNotNull()
        assertThat(apiClient.services).isNotNull()
    }

    @Test
    fun setBaseUrl() {
        val baseUrl = "http://base.url"
        val client = mock<OkHttpClient>()
        val retrofit = apiClient.createRetrofit(baseUrl, client)
        assertThat(retrofit.baseUrl()).isEqualTo(HttpUrl.parse(baseUrl))
    }

    @Test
    fun createInterceptorProd() {
        val interceptor = apiClient.createHttpLoggingInterceptor(false)
        assertThat(interceptor.level).isEqualTo(HttpLoggingInterceptor.Level.NONE)
    }

    @Test
    fun createOkHttpClient() {
        val interceptor = mock<HttpLoggingInterceptor>()
        val okHttpClient = apiClient.createOkHttpClient(interceptor)
        assertThat(okHttpClient.interceptors()).containsExactly(interceptor)
        assertThat(okHttpClient.connectTimeoutMillis()).isEqualTo(30000)
        assertThat(okHttpClient.readTimeoutMillis()).isEqualTo(30000)
    }
}
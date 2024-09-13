package com.chaaba.network

import android.util.Log
import com.chaaba.domain.ports.network.MealNetworkPort
import com.chaaba.network.adapter.MealNetworkAdapter
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module


object NetworkModule {
    private const val TIME_OUT = 6000

    private val portModule = module {
        single<MealNetworkPort> { MealNetworkAdapter(get()) }
    }

    private val ktorModule = module {
        //http
        single {
            HttpClient(Android) {
                install(JsonFeature)
                {
                    KotlinxSerializer(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })

                    engine {
                        connectTimeout = TIME_OUT
                        socketTimeout = TIME_OUT
                    }

                    //Logging
                    install(Logging) {
                        logger = object : Logger {
                            override fun log(message: String) {
                                Log.d("HttpLogging:", message)
                            }

                        }
                    }

                    //Http Response
                    install(ResponseObserver) {
                        onResponse { response ->
                            Log.d("HTTP status:", "${response.status.value}")
                        }
                    }

                    // Headers
                    install(DefaultRequest) {
                        header(HttpHeaders.ContentType, ContentType.Application.Json)
                    }

                }

            }

        }
    }

    val modules: List<Module> = listOf(
        ktorModule, portModule
    )

}
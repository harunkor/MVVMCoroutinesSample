package com.harunkor.mvvmcoroutinessample.di

import com.harunkor.mvvmcoroutinessample.data.local.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    fun provideBaseURL():String {
        return "https://5e510330f2c0d300147c034c.mockapi.io/"
    }

    @Provides
    fun provideLogginInterceptor( ): HttpLoggingInterceptor {
        return  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.callTimeout(40,TimeUnit.SECONDS)
        okHttpClient.connectTimeout(40,TimeUnit.SECONDS)
        okHttpClient.readTimeout(40,TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)

        return okHttpClient.build()
    }

    @Provides
    fun provideConverterFactory() : Converter.Factory {
        return  GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit (okHttpClient: OkHttpClient,baseUrl: String,converterFactory: Converter.Factory ):Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

    }

    @Provides
    fun provideRestApiService(retrofit: Retrofit): ApiService {
        return  retrofit.create(ApiService::class.java)
    }



}
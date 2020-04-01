package com.pia.appetiser.test.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pia.appetiser.test.data.source.remote.ApiService
import com.pia.appetiser.test.presentation.di.PerApplication
import com.pia.appetiser.test.presentation.util.ROOT_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

    @PerApplication
    @Provides
    fun provideRestService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideLogger(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Provides
    fun provideOkHttpCallFactory(httpLoggingInterceptor: HttpLoggingInterceptor): Call.Factory =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @PerApplication
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()


    @Provides
    fun provideRetrofit(
        @Named("endpoint") endpoint: String,
        gson: Gson,
        callFactory: Call.Factory
    ): Retrofit =
        Retrofit.Builder()
            .callFactory(callFactory)
            .baseUrl(endpoint)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()


    @Provides
    @Named("endpoint")
    fun provideEndpoint(): String = ROOT_URL

}
package com.example.gm_challenge.di

import com.example.gm_challenge.service.ArtistApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://itunes.apple.com/"

    @Singleton
    @Provides
    fun provideClient() : OkHttpClient = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }.let {
            OkHttpClient.Builder().addInterceptor(it).build()
        }

    @Singleton
    @Provides
    fun provideArtistApi(client: OkHttpClient) : ArtistApi  = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create(ArtistApi::class.java)
}
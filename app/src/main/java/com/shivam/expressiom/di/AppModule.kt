package com.shivam.expressiom.di

import com.shivam.expressiom.Network.ApiServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesUrl() = "https://jsonplaceholder.typicode.com/"
/*

    @Provides
    fun newProvider() = "http://api.mathjs.org/"
*/

    @Provides
    @Singleton
    fun providesApiService(url : String) : ApiServiceInterface =
                Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiServiceInterface::class.java)

}
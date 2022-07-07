package com.world.fampayassignment.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.world.fampayassignment.network.NetworkInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

   @Singleton
   @Provides
   fun provideGsonBuilder() : Gson{
       return GsonBuilder()
           .create()
   }


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create(gson))


    }

    @Singleton
    @Provides
    fun provideDataService(retrofit : Retrofit.Builder) : NetworkInterface {
        return retrofit
            .build()
            .create(NetworkInterface::class.java)
    }

}
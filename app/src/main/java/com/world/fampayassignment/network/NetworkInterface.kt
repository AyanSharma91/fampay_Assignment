package com.world.fampayassignment.network


import com.world.fampayassignment.model.CardModel
import retrofit2.Response
import retrofit2.http.*

interface NetworkInterface {

    @GET("04a04703-5557-4c84-a127-8c55335bb3b4")
    suspend fun  getCardData() : Response<CardModel>

}
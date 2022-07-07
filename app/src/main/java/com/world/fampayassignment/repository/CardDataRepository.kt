package com.world.fampayassignment.repository

import com.world.fampayassignment.model.CardModel
import com.world.fampayassignment.network.NetworkInterface
import com.world.fampayassignment.sealedClasses.DataState


class CardDataRepository
constructor(
    var networkInterface: NetworkInterface
    )
{

    suspend fun getCardData(): DataState<CardModel> {
        return try {
            val response = networkInterface.getCardData()
            val result = response.body()
            if (response.isSuccessful) {
                val cardDataDetails = result!!
                DataState.Success(cardDataDetails)
            } else {
                DataState.Error(response.message())
            }
        } catch (e: Exception) {
            DataState.Error(e.message ?: "An error occurred")
        }
    }


}
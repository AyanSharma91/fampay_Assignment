package com.world.fampayassignment.model

import com.google.gson.annotations.SerializedName



data class CardModel(
    @SerializedName("card_groups")
    val cardGroups: List<CardGroup>
)
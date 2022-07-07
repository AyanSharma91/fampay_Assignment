package com.world.fampayassignment.model

import com.google.gson.annotations.SerializedName


data class CardGroup(
    @SerializedName("card_type")
    val cardType: Int,
    @SerializedName("cards")
    val cards: List<Card>,
    @SerializedName("design_type")
    val designType: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_scrollable")
    val isScrollable: Boolean,
    @SerializedName("level")
    val level: Int,
    @SerializedName("name")
    val name: String
)
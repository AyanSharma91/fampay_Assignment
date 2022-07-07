package com.world.fampayassignment.model

import com.google.gson.annotations.SerializedName


data class FormatEntity(
    @SerializedName("color")
    val color: String,
    @SerializedName("text")
    val text: String
)
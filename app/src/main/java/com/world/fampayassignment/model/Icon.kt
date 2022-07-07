package com.world.fampayassignment.model

import com.google.gson.annotations.SerializedName


data class Icon(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double,
    @SerializedName("image_type")
    val imageType: String,
    @SerializedName("image_url")
    val imageUrl: String
)
package com.world.fampayassignment.model

import com.google.gson.annotations.SerializedName


data class FormattedDescription(
    @SerializedName("align")
    val align: String,
    @SerializedName("entities")
    val entities: List<FormatEntity>,
    @SerializedName("text")
    val text: String
)
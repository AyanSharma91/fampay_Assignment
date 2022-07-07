package com.world.fampayassignment.model

import com.google.gson.annotations.SerializedName


data class Card(
    @SerializedName("bg_color")
    val bgColor: String,
    @SerializedName("bg_image")
    val bgImage: BgImage,
    @SerializedName("cta")
    val cta: List<Cta>,
    @SerializedName("description")
    val description: String,
    @SerializedName("formatted_description")
    val formattedDescription: FormattedDescription,
    @SerializedName("formatted_title")
    val formattedTitle: FormattedTitle,
    @SerializedName("icon")
    val icon: Icon,
    @SerializedName("is_disabled")
    val isDisabled: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)
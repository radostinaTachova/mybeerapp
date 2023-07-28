package com.example.mybeerapp.data.model


data class BeerApiModel(
    val id: Int,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String,
    val image_url: String?,
    val abv: Float,
)

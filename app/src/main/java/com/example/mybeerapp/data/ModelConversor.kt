package com.example.mybeerapp.data

import com.example.mybeerapp.data.model.BeerApiModel
import com.example.mybeerapp.data.model.BeerModel


fun BeerApiModel.toBeerModel(): BeerModel {
    return BeerModel(
        id = this.id,
        name = this.name,
        tagline = this.tagline,
        first_brewed = this.first_brewed,
        description = this.description,
        image_url = this.image_url,
        abv = this.abv
    )
}
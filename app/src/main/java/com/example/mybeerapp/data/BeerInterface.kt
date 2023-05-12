package com.example.mybeerapp.data

import com.example.mybeerapp.data.model.BeerApiModel


interface BeerInterface {
    suspend fun getBeers(name: String): List<BeerApiModel>
    suspend fun getBeer(id: Int): BeerApiModel?
}
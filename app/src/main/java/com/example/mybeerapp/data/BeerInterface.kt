package com.example.mybeerapp.data

import com.example.mybeerapp.data.model.BeerModel


interface BeerInterface {
    suspend fun getBeers(name: String): List<BeerModel>
    suspend fun getBeer(id: Int): BeerModel?
}
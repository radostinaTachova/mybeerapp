package com.example.mybeerapp.data

import com.example.mybeerapp.data.api.PunkApi
import com.example.mybeerapp.data.model.BeerApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRepository @Inject constructor(private val beersService: PunkApi): BeerInterface {

    companion object {
        @Volatile private var instance: BeerRepository? = null

        fun getInstance(beersService: PunkApi) =
            instance ?: synchronized(this) {
                instance ?: BeerRepository(beersService).also { instance = it }
            }
    }

    override suspend fun getBeers(name: String): List<BeerApiModel> {
        return withContext(Dispatchers.IO) {
            val response = beersService.getBeers(name)
            if (response.isSuccessful) {
                response.body() ?: emptyList<BeerApiModel>()
            } else {
                emptyList<BeerApiModel>()
            }
        }
    }

    override suspend fun getBeer(id: Int): BeerApiModel? {
        return withContext(Dispatchers.IO) {
            val response = beersService.getBeer(id)
            if (response.isSuccessful) {
                val result = response.body()?.first()
                result
            } else {
                null
            }
        }
    }


}
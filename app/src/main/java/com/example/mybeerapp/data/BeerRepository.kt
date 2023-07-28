package com.example.mybeerapp.data

import com.example.mybeerapp.data.api.PunkApi
import com.example.mybeerapp.data.model.BeerModel
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

    override suspend fun getBeers(name: String): List<BeerModel> {
        return withContext(Dispatchers.IO) {
            val response = beersService.getBeers(name)
            if (response.isSuccessful) {
                response.body()?.map { it.toBeerModel() } ?: emptyList<BeerModel>()
            } else {
                emptyList<BeerModel>()
            }
        }
    }

    override suspend fun getBeer(id: Int): BeerModel? {
        return withContext(Dispatchers.IO) {
            val response = beersService.getBeer(id)
            if (response.isSuccessful) {
                val result = response.body()?.first()?.toBeerModel()
                result
            } else {
                null
            }
        }
    }


}
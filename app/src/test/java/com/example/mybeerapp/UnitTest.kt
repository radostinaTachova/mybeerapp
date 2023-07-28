package com.example.mybeerapp

import com.example.mybeerapp.data.model.BeerApiModel
import com.example.mybeerapp.data.model.BeerModel
import com.example.mybeerapp.data.toBeerModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun testBeerApiModel_toModel() {
       val apimodel =  BeerApiModel(
            id = 1,
            name = "name",
            tagline = "tagline",
            first_brewed = "first_brewed",
            description = "description",
            image_url = "image_url",
            abv = 2.3F
        )

       val beermodel = apimodel.toBeerModel()
        assertEquals(apimodel.id, beermodel.id)
        assertEquals(apimodel.name, beermodel.name)
        assertEquals(apimodel.tagline, beermodel.tagline)
        assertEquals(apimodel.first_brewed, beermodel.first_brewed)
        assertEquals(apimodel.description, beermodel.description)
        assertEquals(apimodel.image_url, beermodel.image_url)
        assertEquals(apimodel.abv, beermodel.abv)
    }
}
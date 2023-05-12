package com.example.mybeerapp.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.mybeerapp.data.model.BeerApiModel
import com.example.mybeerapp.ui.compose.SearchBar
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun search_emptyText() {
        composeTestRule.setContent {
            SearchBar("",
                onTextChange = {},
                listOfResults = emptyList(),
                onBeerClick = {} )
        }
        composeTestRule.onNodeWithText("Search...").assertExists()
    }

    @Test
    fun search_withResults() {
        val listOfBeers = listOf<BeerApiModel>(
            BeerApiModel(1,"Name",
                "tagline",
                "first_brewed",
                "description",
                "image_url",
                6.6F
                )
        )
        composeTestRule.setContent {
            SearchBar("",
                onTextChange = {},
                listOfResults = listOfBeers,
                onBeerClick = {} )
        }
        composeTestRule.onNodeWithText("Name").assertExists()
    }

}
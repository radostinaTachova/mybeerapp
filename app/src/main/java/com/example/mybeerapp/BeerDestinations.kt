package com.example.mybeerapp

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.mybeerapp.data.model.BeerApiModel


interface BeerDestination {
    val route: String
}


object Search : BeerDestination {
    override val route = "SearchScreen"
}

object Details : BeerDestination {
    override val route = "details"
    const val beerIdArg = "selected_beer_id"
    val routeWithArgs = "${route}/{${beerIdArg}}"
    val arguments = listOf(
        navArgument(beerIdArg) { type = NavType.IntType }
    )
}

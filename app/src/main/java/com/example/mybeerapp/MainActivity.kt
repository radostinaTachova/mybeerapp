package com.example.mybeerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mybeerapp.ui.compose.BeerDetailScreen
import com.example.mybeerapp.ui.compose.SearchBarScreen
import com.example.mybeerapp.ui.theme.MyBeerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBeerApp()
        }
    }
}

@Composable
fun MyBeerApp() {
    MyBeerAppTheme {
        val navController = rememberNavController()
        Scaffold {
             NavHost(
                    navController = navController,
                    startDestination = Search.route,
                    modifier = Modifier.padding(it)
                ) {
                    composable(route = Search.route) {
                        SearchBarScreen() { idBeer ->
                            navController.navigate("${Details.route}/$idBeer")
                        }
                    }
                    composable(
                        route = Details.routeWithArgs,
                        arguments = Details.arguments
                    ) {
                        BeerDetailScreen()
                    }
             }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyBeerApp()
}
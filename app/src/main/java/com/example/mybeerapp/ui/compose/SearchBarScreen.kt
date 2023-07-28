package com.example.mybeerapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mybeerapp.R
import com.example.mybeerapp.data.model.BeerModel
import com.example.mybeerapp.ui.viewmodel.ListBeersViewModel
import com.example.mybeerapp.ui.theme.*


@Composable
fun SearchBarScreen(beersViewModel: ListBeersViewModel = hiltViewModel(), onBeerClick: (Int) -> Unit) {
    val searchText by beersViewModel.searchText.collectAsState()
    val beers by beersViewModel.beers.collectAsState()
    SearchBar(text = searchText , onTextChange = beersViewModel::onSearchTextChange, beers, onBeerClick)
}


@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    listOfResults: List<BeerModel>,
    onBeerClick: (Int) -> Unit,
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
    ) {

        TextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            shape = Shapes.large,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.5F),
                    text = stringResource(id = R.string.search_your_beer),
                    color = Color.Black)
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search_icon_content_description),
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = PrimaryGreen100,
                textColor = PrimaryGreen900
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth() ) {
            items(listOfResults) {
                SearchResultItem(imageUrl = it.image_url, name = it.name, id = it.id , onBeerClick)
            }
        }
    }
}

@Composable
fun SearchResultItem(imageUrl: String?, name: String, id: Int, onBeerClick: (Int) -> Unit) {
    Column(modifier = Modifier
        .padding(5.dp)
        .clickable { onBeerClick(id) }) {
        Divider(color = Color.Gray, thickness = 2.dp)
        Row(modifier = Modifier
            .background(PrimaryGreen50)
            .fillMaxWidth()
            .padding(5.dp)
        ) {
            AsyncImage(model = imageUrl,
                contentDescription = stringResource(id = R.string.beer_image_content_description),
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)
                    .align(CenterVertically)
                    .padding(5.dp),
                contentScale = ContentScale.Inside,
                error = painterResource(id = R.drawable.baseline_photo_24),
                placeholder = painterResource(id = R.drawable.baseline_photo_24)
                )
            Text(text = name,
                color = PrimaryGreen900,
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(20.dp)
                )
        }
        Divider(color = Color.Gray, thickness = 2.dp)
    }
}


@Preview
@Composable
private fun SearchBarPreview() {
    MyBeerAppTheme {
        SearchResultItem("https://placebear.com/g/200/200", "Prueba", 23, {})
    }
}
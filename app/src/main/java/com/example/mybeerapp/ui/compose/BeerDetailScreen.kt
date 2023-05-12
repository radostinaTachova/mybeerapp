package com.example.mybeerapp.ui.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mybeerapp.R
import com.example.mybeerapp.data.model.BeerApiModel
import com.example.mybeerapp.ui.theme.MyBeerAppTheme
import com.example.mybeerapp.ui.theme.PrimaryGreen900
import com.example.mybeerapp.ui.theme.Shapes
import com.example.mybeerapp.ui.viewmodel.BeerDetailViewModel

@Composable
fun BeerDetailScreen(viewModel: BeerDetailViewModel = hiltViewModel()) {
    val beer =  viewModel.beer.observeAsState().value
    DetailsView(beer = beer)
}

@Composable
fun DetailsView(beer: BeerApiModel?) {
    Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
        //First part
        Row() {
            AsyncImage(
                model = beer?.image_url,
                contentDescription = stringResource(id = R.string.beer_image_content_description),
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)
                    .align(CenterVertically)
                    .padding(5.dp),
                contentScale = ContentScale.Inside,
                error = painterResource(id = R.drawable.baseline_photo_24),
                placeholder = painterResource(id = R.drawable.baseline_photo_24)
            )
            Column(modifier = Modifier
                .align(CenterVertically)
                .padding(start = 20.dp)) {
                Text(
                    text = beer?.name ?: "",
                    color = PrimaryGreen900,
                    fontSize = 20.sp
                )
                Text(
                    text = beer?.tagline ?: "",
                    color = Color.Gray,
                )
                Card(
                    elevation = 2.dp,
                    shape = Shapes.large,
                ) {
                    Text(text = "ABV " + beer?.abv.toString(), modifier = Modifier.padding(5.dp))
                }
            }
        }
        Divider(
            color = Color.Gray,
            thickness = 2.dp,
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        )
        Text(
            text = stringResource(id = R.string.beer_description_title),
            color = PrimaryGreen900,
            fontSize = 20.sp
        )
        Text(text = beer?.description ?: "")
        Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.padding(top = 10.dp))
    }
}



@Preview
@Composable
private fun SearchBarPreview() {
    MyBeerAppTheme {
        BeerDetailScreen()
    }

}
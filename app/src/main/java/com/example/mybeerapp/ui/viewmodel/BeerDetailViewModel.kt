package com.example.mybeerapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.mybeerapp.data.BeerInterface
import com.example.mybeerapp.data.model.BeerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BeerDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val respository: BeerInterface) : ViewModel() {

    private val beerId: Int? = savedStateHandle["selected_beer_id"]

    private val _beer = MutableLiveData<BeerModel?>()
    val beer: LiveData<BeerModel?>
        get() = _beer

    init {
        viewModelScope.launch {
            beerId?.let {
                val selectedBeer = respository.getBeer(it)
                _beer.value = selectedBeer
            }
        }
    }
}
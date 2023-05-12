package com.example.mybeerapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybeerapp.data.BeerInterface
import com.example.mybeerapp.data.BeerRepository
import com.example.mybeerapp.data.model.BeerApiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class ListBeersViewModel @Inject constructor(private val respository: BeerInterface) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val beers = searchText.mapLatest { text -> respository.getBeers(text) }
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(1000),
            emptyList<BeerApiModel>())


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}
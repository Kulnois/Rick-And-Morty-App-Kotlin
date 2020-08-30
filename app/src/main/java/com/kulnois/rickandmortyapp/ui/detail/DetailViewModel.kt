package com.kulnois.rickandmortyapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kulnois.rickandmortyapp.data.model.RickAndMorty

/**
 * Created by @kulnois on 28/08/2020.
 */

class DetailViewModel(@Suppress("UNUSED_PARAMETER")rickAndMorty: RickAndMorty, app: Application) : AndroidViewModel(app) {

    private val _selectedItem = MutableLiveData<RickAndMorty>()
    val selectedItem: LiveData<RickAndMorty>
        get() = _selectedItem

    init {
        _selectedItem.value = rickAndMorty
    }


}
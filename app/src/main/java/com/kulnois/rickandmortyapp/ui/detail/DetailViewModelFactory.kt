package com.kulnois.rickandmortyapp.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kulnois.rickandmortyapp.data.model.RickAndMorty

/**
 * Created by @kulnois on 28/08/2020.
 */

class DetailViewModelFactory(
    private val rickAndMorty: RickAndMorty,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(rickAndMorty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
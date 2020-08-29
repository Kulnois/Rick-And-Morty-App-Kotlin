package com.kulnois.rickandmortyapp.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by @kulnois on 28/08/2020.
 */

class DetailViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
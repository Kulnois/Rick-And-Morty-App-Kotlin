package com.kulnois.rickandmortyapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kulnois.rickandmortyapp.data.model.RickAndMorty
import com.kulnois.rickandmortyapp.network.RickAndMortyApi
import com.kulnois.rickandmortyapp.util.RickAndMortyStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by @kulnois on 28/08/2020.
 */

class ListViewModel : ViewModel() {

    private val _status = MutableLiveData<RickAndMortyStatus>()
    val status: LiveData<RickAndMortyStatus>
        get() = _status


    private val _rickAndMortyData = MutableLiveData<List<RickAndMorty>>()
    val rickAndMortyData: LiveData<List<RickAndMorty>>
        get() = _rickAndMortyData

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getRickAndMortyData()
    }

    private fun getRickAndMortyData() {
        coroutineScope.launch {
            try {
                _status.value = RickAndMortyStatus.LOADING
                var getRickAndMorty = RickAndMortyApi.retrofitService.getData()
                if (getRickAndMorty.isSuccessful && getRickAndMorty.body() != null){
                    val dataRickAndMorty = getRickAndMorty.body()
                    if (dataRickAndMorty!!.results!!.isNotEmpty()) {
                        _status.value = RickAndMortyStatus.DONE
                        _rickAndMortyData.value = dataRickAndMorty.results
                    }
                }else {
                    _status.value = RickAndMortyStatus.ERROR
                    _rickAndMortyData.value = ArrayList()
                }

            } catch (e: Exception){
                _status.value = RickAndMortyStatus.ERROR
                _rickAndMortyData.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
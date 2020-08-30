package com.kulnois.rickandmortyapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by @kulnois on 29/08/2020.
 */
@Parcelize
data class DataRickAndMorty(
    val info: @RawValue Info,
    val results: ArrayList<RickAndMorty>
) : Parcelable
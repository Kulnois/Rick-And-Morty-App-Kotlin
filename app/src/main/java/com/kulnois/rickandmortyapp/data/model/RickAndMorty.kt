package com.kulnois.rickandmortyapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by @kulnois on 29/08/2020.
 */

@Parcelize
data class RickAndMorty(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: @RawValue Data,
    val location: @RawValue Data,
    val image: String,
    val episode: ArrayList<String>
) : Parcelable {}
package com.kulnois.rickandmortyapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by @kulnois on 29/08/2020.
 */

@Parcelize
data class RickAndMorty(
    val id: String
) : Parcelable {}
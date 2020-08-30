package com.kulnois.rickandmortyapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kulnois.rickandmortyapp.adapter.RickAndMortyAdapter
import com.kulnois.rickandmortyapp.data.model.RickAndMorty
import com.kulnois.rickandmortyapp.util.ItemStatus
import com.kulnois.rickandmortyapp.util.RickAndMortyStatus

/**
 * Created by @kulnois on 29/08/2020.
 */

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: ArrayList<RickAndMorty>?) {
    val adapter = recyclerView.adapter as RickAndMortyAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.image_background)
                .error(R.drawable.ic_broken_image))
            .into(imgView)

    }
}

@BindingAdapter("status")
fun bindName(imgView: ImageView, status: String?){
    when (status) {
        ItemStatus.ALIVE.toString() -> {
            imgView.setColorFilter(ContextCompat.getColor(imgView.context, R.color.alive))
        }
        ItemStatus.DEAD.toString() -> {
            imgView.setColorFilter(ContextCompat.getColor(imgView.context, R.color.dead))
        }
        ItemStatus.UNKNOWN.toString() -> {
            imgView.setColorFilter(ContextCompat.getColor(imgView.context, R.color.unknown))
        }
    }
}

@BindingAdapter("rickAndMortyApiStatus")
fun bindStatus(statusImageView: ImageView, status: RickAndMortyStatus?) {
    when (status) {
        RickAndMortyStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
        }
        RickAndMortyStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_image)
        }
        RickAndMortyStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("rickAndMortyApiStatusProgress")
fun bindStatusProgress(contentLoadingProgressBar: ContentLoadingProgressBar, status: RickAndMortyStatus?) {
    when (status) {
        RickAndMortyStatus.LOADING -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                contentLoadingProgressBar.tooltipText = "Loading...."
            }
            contentLoadingProgressBar.visibility = View.VISIBLE
        }
        RickAndMortyStatus.ERROR -> {
            contentLoadingProgressBar.visibility = View.VISIBLE
        }
        RickAndMortyStatus.DONE -> {
            contentLoadingProgressBar.visibility = View.GONE
        }
    }
}
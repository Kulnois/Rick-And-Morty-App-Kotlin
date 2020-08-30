package com.kulnois.rickandmortyapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kulnois.rickandmortyapp.R
import com.kulnois.rickandmortyapp.adapter.RickAndMortyAdapter
import com.kulnois.rickandmortyapp.databinding.FragmentListBinding

/**
 * Created by @kulnois on 28/08/2020.
 */

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentListBinding.inflate(inflater)
        initUI()
        return binding.root
    }

    private fun initUI() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosGrid.adapter = RickAndMortyAdapter(RickAndMortyAdapter.OnClickListener {

        })

        Glide.with( binding.loadingImage.context)
            .load(R.drawable.loading_app)
            .into( binding.loadingImage)

    }
}
package com.kulnois.rickandmortyapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kulnois.rickandmortyapp.R
import com.kulnois.rickandmortyapp.adapter.RickAndMortyAdapter
import com.kulnois.rickandmortyapp.databinding.FragmentListBinding

/**
 * Created by @kulnois on 28/08/2020.
 */

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var lastVisibleItemPosition: Int = 0
    private var page: Int = 1

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
        binding.itemGrid.adapter = RickAndMortyAdapter(RickAndMortyAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        Glide.with( binding.loadingImage.context)
            .load(R.drawable.rick_nad_morty_background)
            .into(binding.loadingImage)

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        viewModel.loadPage.observe(viewLifecycleOwner, Observer { loadPage ->
            if (loadPage) {
                setRecyclerViewScrollListener()
            }
        })

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView!!, newState)
                val totalItemCount = recyclerView!!.layoutManager!!.itemCount
                lastVisibleItemPosition = (binding.itemGrid.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    page += 1
                    viewModel.updatePage(page)
                    binding.itemGrid.removeOnScrollListener(scrollListener)
                }
            }
        }
        binding.itemGrid.addOnScrollListener(scrollListener)
    }
}
package com.example.assignment.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.view.adapter.PostAdapter
import com.example.assignment.view.adapter.PostAdapter2
import com.example.assignment.view.view_model.ContentViewModel
import com.example.assignment.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel : ContentViewModel by activityViewModels()

    private val adapter = PostAdapter()
    private val adapter2 = PostAdapter2()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment("Hello from Home!")
            findNavController().navigate(action)
        }

//        setupPaginatedList()
        setupPaginatedListWithCache()
    }

    private fun setupPaginatedList() {
        binding.recyclerView.adapter = adapter
        viewModel.fetchPostsLiveData()
        lifecycleScope.launch {
            viewModel.fetchPostsLiveData().observe(viewLifecycleOwner) { pagingData ->
                adapter.submitData(lifecycle, pagingData)
            }
        }
    }

    private fun setupPaginatedListWithCache() {
        binding.recyclerView.adapter = adapter2
        lifecycleScope.launch {
            viewModel.fetchPosts().observe(viewLifecycleOwner) { pagingData ->
                adapter2.submitData(lifecycle, pagingData)
            }
        }
    }

    private fun gotoDetailsFragment(data: String){
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(data)
        findNavController().navigate(action)
    }
}
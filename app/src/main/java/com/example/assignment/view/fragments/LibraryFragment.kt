package com.example.assignment.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.assignment.databinding.FragmentLibraryBinding
import com.example.assignment.view.view_model.ContentViewModel


class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding
    private val viewModel: ContentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val info = arguments?.let { LibraryFragmentArgs.fromBundle(it).info }
        binding.tv.text = info

//        viewModel.users.observe(viewLifecycleOwner) {
//            when (it) {
//                is AppUiState.Loading -> {
//                    Log.d("LibFragment", "Loading..")
//                }
//                is AppUiState.Loaded -> {
//                    Log.d("LibFragment", "Loaded..")
//                    Log.d("LibFragment", "Loaded..${it.data}")
//                }
//                is AppUiState.Error -> {
//                    Log.d("LibFragment", "Error..")
//                }
//            }
//        }
    }
}
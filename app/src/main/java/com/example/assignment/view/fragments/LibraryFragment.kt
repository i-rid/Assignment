package com.example.assignment.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.assignment.databinding.FragmentLibraryBinding
import com.example.assignment.view.view_model.ContentViewModel
import com.example.assignment.work.FetchUsersWork
import java.util.concurrent.TimeUnit


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

    fun scheduleFetchUserWork(context: Context) {
        val workManager = WorkManager.getInstance(context)

        // Initial fetch for the first 10 users
        val initialWork = OneTimeWorkRequestBuilder<FetchUsersWork>()
            .setInputData(workDataOf("skip" to 0))
            .build()

        // Periodic work for fetching additional users every 1–2 minutes
        val periodicWork = PeriodicWorkRequestBuilder<FetchUsersWork>(2, TimeUnit.MINUTES)
            .setInputData(workDataOf("skip" to 10)) // Adjust skip dynamically if needed
            .build()

        // Enqueue initial work
        workManager.enqueue(initialWork)

        // Enqueue the initial work and then periodic work
        // Enqueue periodic work after the initial fetch is complete
        //Since WorkManager doesn't allow chaining PeriodicWorkRequest with OneTimeWorkRequest
        workManager.getWorkInfoByIdLiveData(initialWork.id).observeForever { workInfo ->
            if (workInfo != null && workInfo.state.isFinished) {
                workManager.enqueue(periodicWork)
            }
        }
    }

}
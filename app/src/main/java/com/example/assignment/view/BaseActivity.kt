package com.example.assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.assignment.databinding.ActivityBaseBinding
import com.example.assignment.view.view_model.ContentViewModel

class BaseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBaseBinding
    private val viewModel : ContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
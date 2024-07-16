package com.example.diaryapp.view

import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diaryapp.data.Diary
import com.example.diaryapp.databinding.ActivityDiaryBinding
import com.example.diaryapp.view.adapter.DiaryAdapter
import com.example.diaryapp.viewmodel.DiaryViewModel

class DiaryActivity : AppCompatActivity() {

    private val binding: ActivityDiaryBinding by lazy {
        ActivityDiaryBinding.inflate(layoutInflater)
    }

    private val viewModel: DiaryViewModel by lazy {
        DiaryViewModel()
    }

    private val adapter: DiaryAdapter by lazy {
        DiaryAdapter(viewModel.getDiaries().toMutableList())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}
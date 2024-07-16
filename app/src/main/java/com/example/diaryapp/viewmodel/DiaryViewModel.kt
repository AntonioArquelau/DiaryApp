package com.example.diaryapp.viewmodel

import com.example.diaryapp.data.Diary
import com.example.diaryapp.repository.DiaryRepositoryInterface
import com.example.diaryapp.repository.impl.FakeDiaryRepositoryImpl

class DiaryViewModel() {
    private val repository: DiaryRepositoryInterface by lazy{
        FakeDiaryRepositoryImpl()
    }

    fun getDiaries(): List<Diary>{
        return repository.getDiaries()
    }
}
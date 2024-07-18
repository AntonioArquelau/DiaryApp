package com.example.diaryapp.viewmodel

import android.content.Context
import com.example.diaryapp.data.Diary
import com.example.diaryapp.repository.DiaryRepositoryInterface
import com.example.diaryapp.repository.impl.DiaryRepositoryImpl
import com.example.diaryapp.repository.impl.FakeDiaryRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryViewModel(val context: Context) {
    private val repository: DiaryRepositoryInterface by lazy{
        DiaryRepositoryImpl(context)
    }

    fun getDiaries(): List<Diary>{
        return repository.getDiaries()
    }

    fun saveDiary(diary: Diary){
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveDiary(diary)
        }
    }

    fun updateDiary(diary: Diary){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateDiary(diary)
        }
    }

    fun deleteDiary(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteDiary(id)
        }
    }
}
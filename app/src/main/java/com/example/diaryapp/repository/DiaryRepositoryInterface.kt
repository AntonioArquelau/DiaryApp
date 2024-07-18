package com.example.diaryapp.repository

import com.example.diaryapp.data.Diary

interface DiaryRepositoryInterface {
    fun getDiaries(): List<Diary>

    suspend fun saveDiary(diary: Diary)

    suspend fun updateDiary(diary: Diary)

    suspend fun deleteDiary(id: Int)
}
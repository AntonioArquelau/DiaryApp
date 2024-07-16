package com.example.diaryapp.repository

import com.example.diaryapp.data.Diary

interface DiaryRepositoryInterface {
    fun getDiaries(): List<Diary>
}
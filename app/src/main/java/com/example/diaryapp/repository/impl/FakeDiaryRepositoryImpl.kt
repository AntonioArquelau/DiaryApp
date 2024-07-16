package com.example.diaryapp.repository.impl

import com.example.diaryapp.data.Diary
import com.example.diaryapp.repository.DiaryRepositoryInterface

class FakeDiaryRepositoryImpl : DiaryRepositoryInterface {

    private val diaryList: MutableList<Diary> by lazy {
        mutableListOf(
            Diary("2018", "first", "my first"),
            Diary("2019", "second", "my second"),
            Diary("2020", "third", "my third")
        )
    }
    override fun getDiaries(): List<Diary> {
       return diaryList
    }
}
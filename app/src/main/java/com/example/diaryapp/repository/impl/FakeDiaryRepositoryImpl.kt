package com.example.diaryapp.repository.impl

import com.example.diaryapp.data.Diary
import com.example.diaryapp.repository.DiaryRepositoryInterface

class FakeDiaryRepositoryImpl : DiaryRepositoryInterface {

    private val diaryList: MutableList<Diary> by lazy {
        mutableListOf(
            Diary(0, "2018", "first", "my first"),
            Diary(1, "2019", "second", "my second"),
            Diary(2, "2020", "third", "my third")
        )
    }
    override fun getDiaries(): List<Diary> {
       return diaryList
    }

    override suspend fun saveDiary(diary: Diary) {
        diaryList.add(diary)
    }

    override suspend fun updateDiary(diary: Diary) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDiary(id: Int) {
        TODO("Not yet implemented")
    }
}
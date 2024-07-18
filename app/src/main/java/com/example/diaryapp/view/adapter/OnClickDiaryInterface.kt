package com.example.diaryapp.view.adapter

import com.example.diaryapp.data.Diary

interface OnClickDiaryInterface {
    fun onClick(diary: Diary)
    fun onClickDelete(id: Int)
}
package com.example.diaryapp.repository.impl

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.example.diaryapp.data.DBHelper
import com.example.diaryapp.data.DatabaseManager.DiaryEntry.COLUMN_DATE
import com.example.diaryapp.data.DatabaseManager.DiaryEntry.COLUMN_DIARY
import com.example.diaryapp.data.DatabaseManager.DiaryEntry.COLUMN_TITLE
import com.example.diaryapp.data.DatabaseManager.DiaryEntry.TABLE_NAME
import com.example.diaryapp.data.DatabaseManager.DiaryEntry._ID
import com.example.diaryapp.data.Diary
import com.example.diaryapp.repository.DiaryRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryRepositoryImpl(val context: Context): DiaryRepositoryInterface {

    private val dbHelper by lazy {
        DBHelper(context)
    }

    private val dbWritable by lazy {
        dbHelper.writableDatabase
    }

    private val dbReadable by lazy {
        dbHelper.writableDatabase
    }

    private val projection = arrayOf(_ID, COLUMN_DATE, COLUMN_TITLE, COLUMN_DIARY)

    override fun getDiaries(): List<Diary> {
        val diaries = mutableListOf<Diary>()
        val cursor = dbReadable.query(TABLE_NAME, projection, null, null, null, null, null)
        while (cursor.moveToNext()){
            diaries.add(
                Diary(
                    cursor.getInt(cursor.getColumnIndexOrThrow(_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIARY)),
                )
            )
        }
        return diaries
    }

    override suspend fun saveDiary(diary: Diary) {
        val values = ContentValues().apply {
            put(COLUMN_DATE, diary.date)
            put(COLUMN_TITLE, diary.title)
            put(COLUMN_DIARY, diary.diary)
        }
        val rowId = dbWritable.insert(TABLE_NAME, null, values)

        CoroutineScope(Dispatchers.Main).launch {
            if (rowId.equals(-1)) {
                Toast.makeText(context, "There is a problem with your diary", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(context, "Diary $rowId has being Saved", Toast.LENGTH_LONG).show()
            }
        }
    }

    override suspend fun updateDiary(diary: Diary) {
        val values = ContentValues().apply {
            put(COLUMN_TITLE, diary.title)
            put(COLUMN_DIARY, diary.diary)
        }

        dbWritable.update(TABLE_NAME,values,"$_ID = ${diary.id}", null)
    }

    override suspend fun deleteDiary(id: Int) {
        val selection = "$_ID = ?"
        dbWritable.delete(TABLE_NAME, selection, arrayOf(id.toString()))
    }
}
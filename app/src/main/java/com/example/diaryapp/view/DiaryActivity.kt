package com.example.diaryapp.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diaryapp.data.Diary
import com.example.diaryapp.databinding.ActivityDiaryBinding
import com.example.diaryapp.databinding.AddDiaryDialogBinding
import com.example.diaryapp.view.adapter.DiaryAdapter
import com.example.diaryapp.view.adapter.OnClickDiaryInterface
import com.example.diaryapp.viewmodel.DiaryViewModel
import java.text.SimpleDateFormat
import java.util.Date

class DiaryActivity : AppCompatActivity(), OnClickDiaryInterface {

    private val binding: ActivityDiaryBinding by lazy {
        ActivityDiaryBinding.inflate(layoutInflater)
    }

    private val dialog: Dialog by lazy {
        Dialog(this)
    }
    private val currentDate : SimpleDateFormat by lazy {
        SimpleDateFormat("EEE, d MMM, yyyy")
    }

    private val dialogBinding : AddDiaryDialogBinding by lazy {
        AddDiaryDialogBinding.inflate(layoutInflater)
    }

    private val viewModel: DiaryViewModel by lazy {
        DiaryViewModel(this)
    }

    private val adapter: DiaryAdapter by lazy {
        DiaryAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.setData(viewModel.getDiaries().toMutableList())
        setupDialog()
        binding.floatingButton.setOnClickListener{
            showDialog()
        }
    }

    private fun setupDialog(){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    private fun showDialog(diary: Diary? = null){
        if(diary != null){
            dialogBinding.currentDateTextview.text = diary.date
            dialogBinding.diaryDateTextviewValue.setText( diary.diary)
            dialogBinding.nameInputTextValue.setText( diary.title)

            dialogBinding.cancelButton.setOnClickListener {
                dialog.dismiss()
            }
            dialogBinding.saveButton.setOnClickListener {
                val diary = Diary(
                    diary?.id!!,
                    dialogBinding.currentDateTextview.text.toString(),
                    dialogBinding.nameInputTextValue.text.toString(),
                    dialogBinding.diaryDateTextviewValue.text.toString()
                )
                viewModel.updateDiary(diary)
                dialog.dismiss()
                adapter.setData(viewModel.getDiaries().toMutableList())
            }
        } else {
            dialogBinding.currentDateTextview.text = currentDate.format(Date())

            dialogBinding.cancelButton.setOnClickListener {
                dialog.dismiss()
            }
            dialogBinding.saveButton.setOnClickListener {
                val diary = Diary(
                    diary?.id!!,
                    dialogBinding.currentDateTextview.text.toString(),
                    dialogBinding.nameInputTextValue.text.toString(),
                    dialogBinding.diaryDateTextviewValue.text.toString()
                )
                viewModel.saveDiary(diary)
                dialog.dismiss()
                adapter.setData(viewModel.getDiaries().toMutableList())
            }
        }
        dialog.show()
    }

    override fun onClick(diary: Diary) {
        showDialog(diary)
    }

    override fun onClickDelete(id: Int) {
        viewModel.deleteDiary(id)
        adapter.setData(viewModel.getDiaries().toMutableList())
    }

}
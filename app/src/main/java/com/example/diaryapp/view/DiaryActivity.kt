package com.example.diaryapp.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diaryapp.data.Diary
import com.example.diaryapp.databinding.ActivityDiaryBinding
import com.example.diaryapp.databinding.AddDiaryDialogBinding
import com.example.diaryapp.view.adapter.DiaryAdapter
import com.example.diaryapp.viewmodel.DiaryViewModel

class DiaryActivity : AppCompatActivity() {

    private val binding: ActivityDiaryBinding by lazy {
        ActivityDiaryBinding.inflate(layoutInflater)
    }

    private val dialog: Dialog by lazy {
        Dialog(this)
    }

    private val dialogBinding : AddDiaryDialogBinding by lazy {
        AddDiaryDialogBinding.inflate(layoutInflater)
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
        setuDialog()
        binding.floatingButton.setOnClickListener{
            showDialog()
        }
    }
    private fun setuDialog(){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    private fun showDialog(){

        dialogBinding.cancelButton.setOnClickListener{
            dialog.dismiss()
        }
        dialogBinding.saveButton.setOnClickListener{
            Toast.makeText(this, "Save", Toast.LENGTH_LONG).show()
        }
        dialog.show()
    }

}
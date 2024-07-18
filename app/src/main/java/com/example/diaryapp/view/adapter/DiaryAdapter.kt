package com.example.diaryapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diaryapp.data.Diary
import com.example.diaryapp.databinding.DiaryRecyclerViewItemBinding

class DiaryAdapter(val onClickDiaryInterface: OnClickDiaryInterface):
    RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {
    private var diaryList: MutableList<Diary> = mutableListOf()

    fun setData(diaryList: MutableList<Diary>){
        this.diaryList = diaryList
        notifyDataSetChanged()
    }
    inner class DiaryViewHolder(itemView: DiaryRecyclerViewItemBinding): RecyclerView.ViewHolder(itemView.root){
        private lateinit var view: View
        private lateinit var diary: Diary
        var date: TextView
        var title: TextView
        var deleteimageButton: ImageButton

        init {
            view = itemView.root
            date = itemView.diaryDateTextview
            title = itemView.titleTextview
            deleteimageButton = itemView.deleteImageButton
        }

        fun bind(diary: Diary){
            this.diary = diary
            date.text = diary.date
            title.text = diary.title

            view.setOnClickListener{
                onClickDiaryInterface.onClick(diary)
            }

            deleteimageButton.setOnClickListener{
                onClickDiaryInterface.onClickDelete(diary.id)
            }

        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiaryAdapter.DiaryViewHolder {
        val itemView =  DiaryRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DiaryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiaryAdapter.DiaryViewHolder, position: Int) {
        val item = diaryList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }
}
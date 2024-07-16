package com.example.diaryapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diaryapp.data.Diary
import com.example.diaryapp.databinding.DiaryRecyclerViewItemBinding

class DiaryAdapter(private var diaryList: MutableList<Diary>):
    RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    inner class DiaryViewHolder(itemView: DiaryRecyclerViewItemBinding): RecyclerView.ViewHolder(itemView.root), View.OnClickListener{
        private lateinit var view: View
        private lateinit var diary: Diary
        var date: TextView
        var title: TextView
        init {
            view = itemView.root
            view.setOnClickListener(this)
            date = itemView.diaryDateTextview
            title = itemView.titleTextview
        }
        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

        fun bind(diary: Diary){
            this.diary = diary
            date.text = diary.date
            title.text = diary.title
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
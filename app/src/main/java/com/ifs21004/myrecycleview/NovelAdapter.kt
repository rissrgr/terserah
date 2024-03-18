package com.ifs21004.myrecycleview


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21004.myrecycleview.databinding.ItemRowNovelBinding

class ListNovelAdapter(private val listNovel: ArrayList<Novel>) :
    RecyclerView.Adapter<ListNovelAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    /*fungsi 1*/
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowNovelBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }
    @SuppressLint("SetTextI18n")

    /*fungsi 2*/
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val novel = listNovel[position]
        holder.binding.ivItemNovel.setImageResource(novel.image)
        holder.binding.tvItemNovel.text = novel.name
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listNovel[holder.adapterPosition])
        }
    }
    override fun getItemCount(): Int = listNovel.size
    class ListViewHolder(var binding: ItemRowNovelBinding) :
        RecyclerView.ViewHolder(binding.root)

    /*fungsi 3*/
    interface OnItemClickCallback {
        fun onItemClicked(data: Novel)
    }
}
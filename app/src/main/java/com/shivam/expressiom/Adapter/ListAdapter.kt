package com.shivam.expressiom.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivam.expressiom.databinding.ItemsListsBinding
import com.shivam.expressiom.models.Post
import javax.inject.Inject

class ListAdapter @Inject constructor() : RecyclerView.Adapter<ListAdapter.MyViewHolder>(){
    lateinit var dataLists : List<Post>
    lateinit var ctx : Context
    fun setData(dataList: List<Post>, ctx : Context){
        this.dataLists = dataList
        this.ctx = ctx
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemsListsBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataLists[position]
        holder.bind(data)
    }
    override fun getItemCount(): Int {
        return dataLists.size
    }
    inner class MyViewHolder(private val binding: ItemsListsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Post){
            binding.item = item
            binding.executePendingBindings()
        }
    }
}
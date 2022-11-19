package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.RvLayoutNewsBinding
import com.example.myapplication.models.Article

class NewsAdapter(var list:List<Article>):Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder (var binding: RvLayoutNewsBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding=RvLayoutNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val resource=list[position]
        with(holder){
            Glide.with(binding.newsImg.context).load(resource.urlToImage).into(binding.newsImg)
            binding.headLineTv.text=resource.title
            binding.descriptionTv.text=resource.description
            binding.author.text=resource.author
        }

    }

    override fun getItemCount(): Int {
       return list.size
    }

    fun submitList(submitList:List<Article>){
        list=submitList
        notifyDataSetChanged()
    }


}
package com.ai.interviewapp.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ai.interviewapp.databinding.ItemPostLayoutBinding
import com.ai.interviewapp.models.Payload
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class DataAdapter : Adapter<DataAdapter.PostViewHolder>(){

    private var list = ArrayList<Payload>()

    inner class PostViewHolder(val binding : ItemPostLayoutBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = list[position]
        Glide.with(holder.itemView.context).load(currentItem.image_url)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.progressBar.visibility = View.GONE
                    return false
                }

            })
            .into(holder.binding.imageView)

        holder.binding.title.text = currentItem.name
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList : List<Payload>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}
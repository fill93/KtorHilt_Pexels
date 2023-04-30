package com.df.ktorpexels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.df.ktorpexels.data.models.VideoPexel
import com.df.ktorpexels.databinding.AdapterVideoBinding
import com.df.ktorpexels.util.ObjImageManager

class VideoAdapter(private val callback: (String) -> Unit): ListAdapter<VideoPexel, VideoAdapter.VideoViewHolder>(DIFF_CALLBACK) {

    class VideoViewHolder(private val binding: AdapterVideoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(videoPexel: VideoPexel, callback: (String) -> Unit) {
            binding.run {
                ObjImageManager.load(binding.ivVideo, videoPexel.imageUrl)
                tvDuration.text = "Duração de: ${videoPexel.duration} segundos"
                tvUserByName.text = "Adicionado por: ${videoPexel.user.name}"
                itemView.setOnClickListener {
                    //(itemView.context as Activity).openUrl(videoPexel.videoFiles.first().link)
                    callback(videoPexel.videoFiles.first().link)
                }
            }
        }

        companion object{
            fun create(parent: ViewGroup): VideoViewHolder {
                val binding = AdapterVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VideoViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position), callback = callback)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<VideoPexel>() {
            override fun areItemsTheSame(oldItem: VideoPexel, newItem: VideoPexel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: VideoPexel, newItem: VideoPexel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
package com.df.ktorpexels

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.df.ktorpexels.adapter.VideoAdapter
import com.df.ktorpexels.databinding.ActivityMainBinding
import com.df.ktorpexels.util.extensions.regexAndClickableLinks
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapterVideos by lazy {
        VideoAdapter(
            callback = {
                playerVideo(it)
            }
        )
    }
    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        setObservers()
        setAdapter()
        setInitPlayer()
    }

    private fun setListeners() {
        binding.run {
            btBuscar.setOnClickListener {
                viewModel.getVideos(etBuscar.text.toString())
            }
        }
    }

    private fun setObservers() {
        viewModel.videoMLD.observe(this) {
            adapterVideos.submitList(it.videos)
        }
        viewModel.loadingMLD.observe(this) {
            binding.loadingVideos.visibility = it
        }
        viewModel.errorMLD.observe(this) {
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAdapter() {
        binding.run {
            rvVideos.layoutManager = LinearLayoutManager(this@MainActivity)
            rvVideos.adapter = adapterVideos
        }
    }

    private fun setInitPlayer() {
        player = SimpleExoPlayer.Builder(this)
            .build()
            .also {
                binding.videoView.player = it
            }
    }

    private fun playerVideo(link: String) {
        binding.clPlayer.visibility = View.VISIBLE
        val uri = Uri.parse(link)
        val media = MediaItem.fromUri(uri)
        player?.setMediaItem(media)
        player?.play()
    }

    override fun onBackPressed() {
        if (binding.clPlayer.visibility == View.VISIBLE) {
            binding.clPlayer.visibility = View.GONE
            player?.stop()
        } else {
            super.onBackPressed()
        }
    }
}
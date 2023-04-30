package com.df.ktorpexels

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.df.ktorpexels.data.models.DataVideos
import com.df.ktorpexels.data.repository.VideoRepository
import com.df.ktorpexels.util.extensions.launchSilent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val videoRepository: VideoRepository,
    private val retString: String
): ViewModel() {

    val videoMLD = MutableLiveData<DataVideos>()
    val loadingMLD by lazy { MutableLiveData<Int>() }
    val errorMLD by lazy { MutableLiveData<Throwable>() }

    init {
        getVideos("Beach")
    }

    fun getVideos(query: String) {
        launchSilent {
            try {
                Log.d("JKK", retString)
                loadingMLD.postValue(View.VISIBLE)
                videoMLD.postValue(videoRepository.getVideos(query))
                loadingMLD.postValue(View.GONE)
            } catch (e: Exception) {
                loadingMLD.postValue(View.GONE)
                errorMLD.postValue(e)
            }
        }
    }
}
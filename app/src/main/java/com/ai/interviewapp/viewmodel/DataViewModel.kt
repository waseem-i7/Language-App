package com.ai.interviewapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ai.interviewapp.models.DataResponse
import com.ai.interviewapp.repository.DataRepository
import com.ai.interviewapp.utils.NetworkResults
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val dataRepository: DataRepository)  : ViewModel(){

    val postLiveData : LiveData<NetworkResults<DataResponse>>
        get() = dataRepository.postLiveData

    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.getData()
        }
    }
}



























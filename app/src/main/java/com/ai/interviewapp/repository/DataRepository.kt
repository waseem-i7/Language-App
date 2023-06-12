package com.ai.interviewapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ai.interviewapp.di.ApiInterface
import com.ai.interviewapp.models.DataResponse
import com.ai.interviewapp.utils.NetworkResults
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiInterface: ApiInterface){
    private val _postLiveData = MutableLiveData<NetworkResults<DataResponse>>()
    val postLiveData : LiveData<NetworkResults<DataResponse>>
        get() = _postLiveData

    suspend fun getData(){

        val result = apiInterface.getData()

        if (result.isSuccessful && result.body() !=null){
            _postLiveData.postValue(NetworkResults.SUCCESS(result.body()))
        }else if (result.errorBody() !=null){
            _postLiveData.postValue(NetworkResults.ERROR(message = "Something went Wrong."))
        }
    }
}


















package com.ai.interviewapp.di

import com.ai.interviewapp.models.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("c2f9f5ba-3940-470f-bd28-915acd13c7a8")
    suspend fun getData() : Response<DataResponse>
}
package com.shivam.expressiom.Network

import com.shivam.expressiom.models.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("posts")
    suspend fun getData() : List<Post>

    @GET("v4")
    suspend fun getExpression(@Body expression : String) : Response<String>
}
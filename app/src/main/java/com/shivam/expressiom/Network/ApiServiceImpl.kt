package com.shivam.expressiom.Network

import com.google.gson.JsonElement
import com.shivam.expressiom.models.Post
import retrofit2.Response
import javax.inject.Inject

class ApiServiceImpl  @Inject constructor(private val apiService: ApiServiceInterface) {

    suspend fun getPost(): List<Post> = apiService.getData()

    suspend fun getExpression(expression : String) = apiService.getExpression(expression)

}
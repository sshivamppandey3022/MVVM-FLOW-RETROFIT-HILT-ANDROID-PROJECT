package com.shivam.expressiom.Repositories

import com.google.gson.JsonElement
import com.shivam.expressiom.Network.ApiServiceImpl
import com.shivam.expressiom.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.xml.xpath.XPathExpression

class Repository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getData() : Flow<List<Post>> = flow{
        emit(apiServiceImpl.getPost())
    }.flowOn(Dispatchers.IO)

    fun getExpression(expression: String) : Flow<Response<String>> = flow {
        emit(apiServiceImpl.getExpression(expression))
    }.flowOn(Dispatchers.IO)
}
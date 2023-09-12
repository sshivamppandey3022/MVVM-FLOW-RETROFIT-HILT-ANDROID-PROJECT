package com.shivam.expressiom.utils

import com.shivam.expressiom.models.Post
import retrofit2.Response

sealed class ApiState{

    object Loading : ApiState()
    class Failure(val msg : Throwable) : ApiState()
    class Sucess(val data : List<Post>) : ApiState()
    class ExpressionSuccess(data: Response<String>) : ApiState()
    object Empty : ApiState()
}

package com.shivam.expressiom.Viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivam.expressiom.Repositories.Repository
import com.shivam.expressiom.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {

    private val postStateFlow : MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    private val postExpressionFlow : MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val _postStateFlow : StateFlow<ApiState> = postStateFlow
    val _postExpressionFlow : StateFlow<ApiState> = postExpressionFlow

    fun getData() = viewModelScope.launch {
        postStateFlow.value =  ApiState.Loading
        repository.getData()
            .catch { e->
                postStateFlow.value = ApiState.Failure(e)
            }.collect{data->
                postStateFlow.value = ApiState.Sucess(data)
            }
    }

    fun getExpression(expression : String) = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        repository.getExpression(expression)
            .catch {e->
                postExpressionFlow.value = ApiState.Failure(e)
            }.collect{data->
                postExpressionFlow.value = ApiState.ExpressionSuccess(data)
            }
    }
}
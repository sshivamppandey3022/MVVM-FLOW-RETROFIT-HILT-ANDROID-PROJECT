package com.shivam.expressiom

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivam.expressiom.Adapter.ListAdapter
import com.shivam.expressiom.Viewmodels.MainViewModel
import com.shivam.expressiom.databinding.ActivityMainBinding
import com.shivam.expressiom.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	private val TAG = "MainActivity"
	lateinit var binding: ActivityMainBinding
	@Inject
	lateinit var myAdapter: ListAdapter
    private val viewModel: MainViewModel by viewModels()

//	private val viewModel by viewModels<MainViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		initRecyclerView()
		viewModel.getData()
		loadListData()

		/*viewModel.getExpression(binding.expression.text.toString())
		binding.find.setOnClickListener {
			evaluateExpression()
		}*/
	}

	private fun evaluateExpression() {
		CoroutineScope(Dispatchers.IO).launch {
			viewModel._postExpressionFlow.collect{
				when(it){
					is ApiState.Loading -> {
						binding.progressBar.visibility = View.VISIBLE
					}
					is ApiState.ExpressionSuccess -> {
//						binding.res.text = it.toString()
					}
					is ApiState.Failure -> {
//						binding.expression.error = "Wrong"
					}

					else -> {}
				}
			}
		}
	}

	private fun loadListData() {
		CoroutineScope(Dispatchers.IO).launch {
			viewModel._postStateFlow.collect {
				when (it) {
					is ApiState.Loading -> {
						binding.progressBar.visibility = View.VISIBLE
						binding.recyclerView.visibility = View.GONE
					}

					is ApiState.Failure -> {
						binding.progressBar.visibility = View.GONE
						binding.recyclerView.visibility = View.GONE
						CoroutineScope(Dispatchers.Main).launch {
							Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
						}
					}

					is ApiState.Sucess -> {
						CoroutineScope(Dispatchers.Main).launch {
							binding.progressBar.visibility = View.GONE
							binding.recyclerView.visibility = View.VISIBLE
							myAdapter.setData(it.data, this@MainActivity)
							Log.d(TAG, "loadListData: ${it.data}")
						}
					}

					is ApiState.Empty -> {

					}

					else -> {}
				}
			}
		}
	}
	private fun initRecyclerView() {
		myAdapter.setData(arrayListOf(), this)
		binding.recyclerView.setHasFixedSize(true)
		val layoutManager = LinearLayoutManager(this)
		binding.recyclerView.layoutManager = layoutManager
		binding.recyclerView.adapter = myAdapter
		/*binding.recyclerView.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(this@MainActivity)
			adapter = myAdapter
		}*/
	}
}
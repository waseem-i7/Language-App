package com.ai.interviewapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.ai.interviewapp.adapter.DataAdapter
import com.ai.interviewapp.databinding.ActivityMainBinding
import com.ai.interviewapp.utils.NetworkResults
import com.ai.interviewapp.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityMainBinding
    private val dataViewModel by viewModels<DataViewModel>()
    private lateinit var adapter : DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        dataViewModel.getData()
        dataViewModel.postLiveData.observe(this){
            when(it){
                is NetworkResults.LOADING ->{
                    binding.progressBar.visibility = View.VISIBLE
                }

                is NetworkResults.SUCCESS ->{
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { it1 -> adapter.updateList(it1.payload) }
                    Log.e("DataResponse",it.data.toString())
                }

                is NetworkResults.ERROR ->{
                    binding.progressBar.visibility = View.GONE
                    it.message?.let { it1 -> Log.e("DataResponse", it1) }
                }
            }
        }
    }


    private fun setUpRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.layoutManager = GridLayoutManager(this,2)
        adapter = DataAdapter()
        binding.recyclerView.adapter = adapter
    }
}





















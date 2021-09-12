package com.wolftiger.todolist.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wolftiger.todolist.databinding.ActivityMainBinding
import com.wolftiger.todolist.datasource.TaskDateSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter

        insertListeners()
    }

    private fun insertListeners() {
        binding.fab.setOnClickListener{
            startActivityForResult(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)
        }

        adapter.listnerEdit = {
            Log.e("TAG", "listenerEdit$it")
        }

        adapter.listnerDelete = {
            Log.e("TAG", "listenerDelete: $it" )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK){
            binding.rvTasks.adapter = adapter
            adapter.submitList(TaskDateSource.getList())
        }
    }

    companion object{
        private const val CREATE_NEW_TASK = 1000
    }
}
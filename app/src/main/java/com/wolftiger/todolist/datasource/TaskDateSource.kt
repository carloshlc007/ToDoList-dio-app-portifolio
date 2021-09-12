package com.wolftiger.todolist.datasource

import com.wolftiger.todolist.model.Task

object TaskDateSource{
    private val list = arrayListOf<Task>()

    fun getList() = list

    fun insertTask(task: Task){
        list.add(task.copy(id = list.size+1))
    }
}
package com.kaushal.mentalhealth.model

import androidx.compose.runtime.mutableStateListOf

class TaskManager {
    private val tasks = mutableStateListOf<TaskModel>();

    fun addTask(task: TaskModel){
        tasks.add(task)
    }
    fun getTasks(): List<TaskModel>{
        return tasks
    }
    fun updateTask(updatedTask: TaskModel){
        val index = tasks.indexOfFirst{ it.id == updatedTask.id }
        if(index != -1){
            tasks[index] = updatedTask
        }
    }
    fun deleteTask(id: String){
        tasks.removeAll{task-> task.id == id}
    }
}
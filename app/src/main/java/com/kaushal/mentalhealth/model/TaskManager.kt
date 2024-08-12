package com.kaushal.mentalhealth.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskManager {
    private val _tasks = MutableStateFlow<List<TaskModel>>(emptyList())
    val tasks: StateFlow<List<TaskModel>> = _tasks.asStateFlow()

    fun addTask(task: TaskModel) {
        _tasks.update { currentTasks ->
            currentTasks + task
        }
    }

    fun getTasks(): List<TaskModel> {
        return _tasks.value
    }

    fun updateTask(updatedTask: TaskModel): Boolean {
        var updated = false
        _tasks.update { currentTasks ->
            currentTasks.map { task ->
                if (task.id == updatedTask.id) {
                    updated = true
                    updatedTask
                } else {
                    task
                }
            }
        }
        return updated
    }

    fun deleteTask(id: String) {
        _tasks.update { currentTasks ->
            currentTasks.filter { it.id != id }
        }
    }
}

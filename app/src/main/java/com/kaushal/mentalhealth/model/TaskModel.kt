package com.kaushal.mentalhealth.model

import androidx.compose.ui.graphics.Color
import java.time.DayOfWeek
import java.time.LocalDateTime

data class TaskModel(
    val id: String = generateId(),
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val title: String,
    val description: String = "",
    val color: Color,
    val tags: Set<String> = emptySet(),
    val schedule: Schedule = Schedule.Once,
    var status: Boolean = false,
    var isNotificationEnabled: Boolean = true
){
    sealed class Schedule{
        data object Once: Schedule()
        data class Daily(val time: LocalDateTime): Schedule()
        data class Weekly(val daysOfWeek: Set<DayOfWeek>, val time: LocalDateTime):Schedule()
        data class Monthly(val dayOfMonth: Int, val time: LocalDateTime):Schedule()
    }

    companion object{
        private var idCounter = 0
        private fun generateId():String = "TASK_${++idCounter}"
    }
}

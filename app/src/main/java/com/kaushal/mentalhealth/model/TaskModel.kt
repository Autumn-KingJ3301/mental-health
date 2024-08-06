package com.kaushal.mentalhealth.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

data class TaskModel(
    val id: String = generateId(),
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val title: String,
    val description: String = "",
    val color: Color = Color.Cyan,
    val tags: Set<String> = setOf("Periodic", "Focus mode"),
    val priority: Priority = Priority.High,
    val schedule: Schedule = Schedule.Once,
    var status: Boolean = false,
    var isNotificationEnabled: Boolean = true
) {
    enum class Priority {
        High, Medium, Low
    }

    sealed class Schedule {
        data object Once : Schedule()

        data class Daily(val time: LocalDateTime) : Schedule()
        data class Weekly(val daysOfWeek: Set<DayOfWeek>, val time: LocalDateTime) : Schedule()
        data class Monthly(val dayOfMonth: Int, val time: LocalDateTime) : Schedule()
    }

    companion object {
        private var idCounter = 0
        private fun generateId(): String = "TASK_${++idCounter}"
    }
}

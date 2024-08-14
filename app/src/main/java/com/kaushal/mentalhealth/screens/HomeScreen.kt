package com.kaushal.mentalhealth.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaushal.mentalhealth.R
import com.kaushal.mentalhealth.model.TaskManager
import com.kaushal.mentalhealth.model.TaskModel
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

val manager = TaskManager()

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen() {

    val sheetState = remember {
        mutableStateOf(false)
    }
    val priorityOptions = listOf("High", "Medium", "Low")
    val tagOptions = setOf("Todo", "Periodic", "Focus mode")

    var selectedPriority by remember { mutableStateOf(priorityOptions[1]) }
    var title by remember { mutableStateOf("") }
    var selectedTags by remember { mutableStateOf(setOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.background))
            .padding(15.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Header()
        Spacer(modifier = Modifier.height(10.dp))
        Calendar()
        TaskFilters()
        Spacer(modifier = Modifier.height(5.dp))

        Tasks(addTaskState = sheetState)
        if (sheetState.value) {
            ModalBottomSheet(
                onDismissRequest = { sheetState.value = false },
                containerColor = colorResource(
                    id = R.color.background_secondary,
                ),

                ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp, 10.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    Text(
                        text = "Add Task",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(
                            id = R.color.text
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            color = colorResource(id = R.color.text)
                        ),
                        maxLines = 1,
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = colorResource(id = R.color.input),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = colorResource(id = R.color.input),
                            unfocusedLabelColor = colorResource(id = R.color.text),
                            focusedTextColor = colorResource(id = R.color.text),
                            focusedLabelColor = colorResource(id = R.color.text_accent)
                        )

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Description") },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            color = colorResource(id = R.color.text)
                        ),
                        maxLines = 5,
                        minLines = 5,
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = colorResource(id = R.color.input),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = colorResource(id = R.color.input),
                            unfocusedLabelColor = colorResource(id = R.color.text),
                            focusedTextColor = colorResource(id = R.color.text),
                            focusedLabelColor = colorResource(id = R.color.text_accent)
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .selectableGroup()
                    ) {
                        Text(
                            text = "Priority",
                            color = colorResource(id = R.color.text),
                            style = MaterialTheme.typography.titleMedium
                        )
                        priorityOptions.forEach { option ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .selectable(
                                        selected = (option == selectedPriority),
                                        onClick = { selectedPriority = option }
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (option == selectedPriority),
                                    onClick = { selectedPriority = option },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = colorResource(id = R.color.text_accent)
                                    )
                                )
                                Text(
                                    text = option,
                                    modifier = Modifier.padding(start = 5.dp),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = colorResource(
                                        id = R.color.text
                                    )
                                )
                            }
                        }
                    }
                    Text(
                        text = "Tags",
                        color = colorResource(id = R.color.text),
                        style = MaterialTheme.typography.titleMedium
                    )
                    FlowRow(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        tagOptions.forEach { tag ->
                            FilterChip(
                                selected = tag in selectedTags,
                                onClick = {
                                    selectedTags =
                                        if (tag in selectedTags) {
                                            selectedTags - tag
                                        } else {
                                            selectedTags + tag
                                        }
                                },
                                label = { Text(text = tag) },
                                colors = FilterChipDefaults.filterChipColors(
                                    labelColor = colorResource(id = R.color.text_accent),
                                    selectedContainerColor = colorResource(id = R.color.text_accent)
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    borderColor = colorResource(id = R.color.dark_accent_2),
                                    selected = tag in selectedTags,
                                    enabled = true
                                ),
                            )
                        }
                    }
                }

            }
        }
    }
}

//Header
@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.avatar),
                contentDescription = "Avatar",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Hello, Jatin",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(
                        id = R.color.text
                    )
                )
                Text(
                    text = "Ascendant 3",
                    style = MaterialTheme.typography.labelLarge,
                    color = colorResource(
                        id = R.color.rank_asc
                    )
                )
            }
        }
        Row {
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.background_secondary),
                        shape = CircleShape
                    )
                    .height(35.dp)
                    .width(35.dp)
            ) {
                Icon(
                    Icons.Outlined.Search, contentDescription = "Search", tint = colorResource(
                        id = R.color.text
                    )
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.background_secondary),
                        shape = CircleShape
                    )
                    .height(35.dp)
                    .width(35.dp)
            ) {
                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = "Notification",
                    tint = colorResource(
                        id = R.color.text
                    )
                )
            }
        }
    }

}

@Composable
fun Calendar() {

    val selectedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    val dates = remember(selectedDate) {
        (-2..2).map { selectedDate.plusDays(it.toLong()) }
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Calendar",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(
                    id = R.color.text
                )
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = colorResource(
                        id = R.color.text
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(
                    color = colorResource(id = R.color.background_secondary),
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            dates.forEach { date ->
                DateItems(
                    date = date, isSelected = date == selectedDate, taskCompleted = 1f
                )
            }
        }
    }
}

@Composable
fun DateItems(
    date: LocalDate, isSelected: Boolean, taskCompleted: Float = 1f
) {
    Box(
        modifier = Modifier
            .width(47.dp)
            .height(72.dp)
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(

                        (if (isSelected) taskCompleted else 0f) to colorResource(id = R.color.dark_accent_2),
                        0.0f to colorResource(id = R.color.background_secondary)
                    )
                ), shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = if (isSelected) R.color.text else R.color.text_accent)
            )
            Text(
                text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = if (isSelected) R.color.text else R.color.text_accent)

            )

        }
    }
}

@Composable
fun Filter(
    todo: MutableState<Boolean>, label: String, color: Int
) {
    FilterChip(
        onClick = { todo.value = !todo.value },
        label = {
            Text(
                label, color = colorResource(id = R.color.text)
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.Transparent,
            selectedContainerColor = colorResource(id = R.color.background_secondary)
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = colorResource(id = R.color.background_secondary),
            selected = todo.value,
            enabled = true
        ),
        selected = todo.value,
        leadingIcon = if (todo.value) {
            {
                Icon(
                    painter = painterResource(id = R.drawable.radiobuttonchecked),
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                    tint = colorResource(id = color)
                )
            }
        } else {
            {
                Icon(
                    painter = painterResource(id = R.drawable.radiobuttonunchecked),
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                    tint = colorResource(id = color)
                )
            }
        },
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskFilters() {
    val todo = remember {
        mutableStateOf(false)
    }
    val focusMode = remember {
        mutableStateOf(false)
    }
    val periodic = remember {
        mutableStateOf(false)
    }

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Filter(todo = periodic, label = "Periodic", color = R.color.rank_asc)
        Filter(todo = todo, label = "To-do", color = R.color.purple_200)
        Filter(todo = focusMode, label = "Focus Mode", color = R.color.teal_200)
    }

}

@Composable
fun Tasks(addTaskState: MutableState<Boolean>) {

    val tasks by manager.tasks.collectAsState()

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Tasks", style = MaterialTheme.typography.titleLarge, color = colorResource(
                    id = R.color.text
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = {
                    addTaskState.value = true
                }, modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.background_secondary),
                        shape = CircleShape
                    )
                    .height(35.dp)
                    .width(35.dp)
            ) {
                Icon(
                    Icons.Outlined.Add, contentDescription = "Search",
                    tint = colorResource(
                        id = R.color.text
                    ),

                    )
            }

        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "High priority",
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(
                    id = R.color.hard_label
                )
            )
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            ) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "DropDown",
                    tint = colorResource(
                        id = R.color.text
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        //Tasks
        LazyColumn {
            items(tasks, key = { it.id }) { task ->
                TaskCard(task = task, onStatusChange = { newStatus ->
                    val updatedTask = task.copy(status = newStatus)
                    manager.updateTask(updatedTask)
                }, onNotificationChange = { newNotification ->
                    val updatedTask = task.copy(isNotificationEnabled = newNotification)
                    manager.updateTask(updatedTask)
                })
            }
        }

    }
}

@Composable
fun Tag(tag: String) {
    Box(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(10.dp, 5.dp)
    ) {
        Text(text = tag)
    }
    Spacer(modifier = Modifier.width(5.dp))
}

@Composable
fun TaskCard(
    task: TaskModel, onStatusChange: (Boolean) -> Unit, onNotificationChange: (Boolean) -> Unit
) {

    var notifications by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(notifications) {
        onNotificationChange(notifications)
    }

    Spacer(modifier = Modifier.height(15.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = task.color, shape = RoundedCornerShape(14.dp))
            .padding(10.dp)

    ) {
        Column {
            Text(text = task.schedule.toString(), style = MaterialTheme.typography.labelSmall)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task.title, style = MaterialTheme.typography.titleLarge)
                Row {
                    task.tags.forEach { tag -> Tag(tag) }
                }

            }


            val desc = if (task.description.length > 50) task.description.substring(0, 50) + "..."
            else task.description

            Text(text = desc, style = MaterialTheme.typography.bodySmall)
            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = task.status,
                    onCheckedChange = { onStatusChange(it) },
                    colors = CheckboxColors(
                        checkedCheckmarkColor = task.color,
                        checkedBoxColor = colorResource(id = R.color.accent),
                        uncheckedBorderColor = colorResource(id = R.color.accent),
                        uncheckedBoxColor = Color.Transparent,
                        checkedBorderColor = colorResource(id = R.color.accent),
                        uncheckedCheckmarkColor = colorResource(id = R.color.accent),
                        disabledBorderColor = Color.Transparent,
                        disabledIndeterminateBorderColor = Color.Transparent,
                        disabledCheckedBoxColor = Color.Transparent,
                        disabledUncheckedBoxColor = Color.Transparent,
                        disabledUncheckedBorderColor = Color.Transparent,
                        disabledIndeterminateBoxColor = Color.Transparent
                    )
                )
                IconButton(
                    onClick = { notifications = !notifications },
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                ) {
                    Icon(
                        if (task.isNotificationEnabled) Icons.Default.Notifications else Icons.Outlined.Notifications,
                        contentDescription = "notification",
                        tint = colorResource(
                            id = R.color.accent
                        ),
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )
                }

            }
        }

    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
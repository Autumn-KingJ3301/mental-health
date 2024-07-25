package com.kaushal.mentalhealth.screens

import android.app.Notification
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun HomeScreen() {
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
        Tasks()
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
    todo: MutableState<Boolean>,
    label: String,
    color: Int
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

    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Filter(todo = periodic, label = "Periodic", color = R.color.rank_asc)
        Filter(todo = todo, label = "To-do", color = R.color.purple_200)
        Filter(todo = focusMode, label = "Focus Mode", color = R.color.teal_200)
    }

}

@Composable
fun Tasks() {
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
                onClick = { /*TODO*/ }, modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.background_secondary),
                        shape = CircleShape
                    )
                    .height(35.dp)
                    .width(35.dp)
            ) {
                Icon(
                    Icons.Outlined.Add, contentDescription = "Search", tint = colorResource(
                        id = R.color.text
                    )
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
        val task = remember {
            mutableStateOf(false)
        }
        // Tasks
        TaskCard(
            dueDate = LocalDate.now(),
            title = "Cooking",
            description = "This is my show",
            tags = listOf("Todo"),
            isCompleted = task,
            notification = task
        )
    }
}

@Composable
fun TaskCard(
    dueDate: LocalDate,
    title: String,
    description: String,
    tags: List<String>,
    isCompleted: MutableState<Boolean>,
    notification: MutableState<Boolean>
) {

}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
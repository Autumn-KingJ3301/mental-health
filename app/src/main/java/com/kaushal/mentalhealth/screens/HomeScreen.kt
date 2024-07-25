package com.kaushal.mentalhealth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaushal.mentalhealth.R


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.background))
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Header()
    }
}

//Header
@Composable
fun Header() {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
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
                    Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = colorResource(
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

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
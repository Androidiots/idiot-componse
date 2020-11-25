package com.androidiots.compose.ui.activity

import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun BasicGridScreen(onBack: () -> Unit) {
    Scaffold(
            topBar = {
                TopAppBar(
                        title = {
                            Text(
                                    text = "Basic Grid",
                                    style = MaterialTheme.typography.subtitle2,
                                    color = contentColor()
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Filled.ArrowBack)
                            }
                        }
                )
            },
            bodyContent = { innerPadding ->
                val modifier = Modifier.padding(innerPadding)
//            PostContent(post, modifier)
            },
            bottomBar = {
//            BottomBar(post) { showDialog = true }
            }
    )
}
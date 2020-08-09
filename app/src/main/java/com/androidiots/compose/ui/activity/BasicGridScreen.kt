package com.androidiots.compose.ui.activity

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.contentColor
import androidx.ui.layout.padding
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack

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
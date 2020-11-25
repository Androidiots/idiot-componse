package com.androidiots.compose.ui.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.androidiots.compose.IdiotComposeApplication
import com.androidiots.compose.R
import com.androidiots.compose.data.DataGenerator
import com.androidiots.compose.data.getMenuItems
import com.androidiots.compose.model.People
import com.androidiots.compose.ui.ThemedPreview

@Composable
fun BasicListScreen(onBack: () -> Unit) {
    var items: MutableList<People?>? = IdiotComposeApplication.appContext?.let { (DataGenerator.getPeopleData(it) as MutableList<People?>?)!! }
    IdiotComposeApplication.appContext?.let { DataGenerator.getPeopleData(it)?.let { items?.addAll(it) } }
    IdiotComposeApplication.appContext?.let { DataGenerator.getPeopleData(it)?.let { items?.addAll(it) } }

    Scaffold(
        topBar = {
            TopAppBar(
                    title = {
                        Text(
                                text = "Basic List",
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
            BasicListContent(items, modifier)
        },
        bottomBar = {
//            BottomBar(post) { showDialog = true }
        }
    )
}

@Composable
fun BasicListContent(items: MutableList<People?>?, modifier: Modifier) {
    VerticalScroller(modifier = modifier.padding(horizontal = 16.dp)) {
        items?.forEach { item ->
            BasicListSection(item)
        }
    }
}

@Composable
fun BasicListSection(item: People?) {
    Row(verticalGravity = Alignment.CenterVertically, modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()) {
        item?.image?.takeIf { it != -1 }?.apply {
            Image(
                    asset = imageResource(item.image),
                    modifier = Modifier.preferredSize(40.dp),
                    contentScale = ContentScale.Inside
            )
        }
        Spacer(Modifier.preferredWidth(8.dp))
        Text(text = item?.name!!, style = MaterialTheme.typography.body1)
    }
}

@Preview("Drawer contents")
@Composable
fun PreviewBasicListContent() {
    var items: MutableList<People?>? = IdiotComposeApplication.appContext?.let { (DataGenerator.getPeopleData(it) as MutableList<People?>?)!! }

    ThemedPreview {
        BasicListContent(
                items = items,
                modifier = Modifier
        )
    }
}


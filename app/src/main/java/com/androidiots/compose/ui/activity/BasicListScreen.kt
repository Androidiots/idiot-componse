package com.androidiots.compose.ui.activity

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
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


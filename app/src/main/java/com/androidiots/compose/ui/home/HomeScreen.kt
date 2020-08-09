package com.androidiots.compose.ui.home

import android.widget.Toast
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
import androidx.ui.material.*

import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.androidiots.compose.R
import com.androidiots.compose.data.ListItem
import com.androidiots.compose.data.getMenuItems
import com.androidiots.compose.ui.AppDrawer
import com.androidiots.compose.ui.Screen
import com.androidiots.compose.ui.ThemedPreview

private val defaultSpacerSize = 16.dp
val myData = listOf("List", "Grid", "Bottom Sheet", "Button", "Card", "Dialogs", "Menu", "Slider", "Snackbar & Toast", "Toolbars", "Profile", "No ItemPage", "Login")

@Composable
fun HomeScreen(
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    val menuItems = getMenuItems()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.Home,
                closeDrawer = { scaffoldState.drawerState = DrawerState.Closed },
                navigateTo = navigateTo
            )
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Idiot Compose") },
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = DrawerState.Opened }) {
                        Icon(imageResource(R.drawable.jetpack_logo))
                    }
                }
            )
        },
        bodyContent = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            HomeScreenContent(navigateTo, modifier, menuItems)
        }
    )
}

@Composable
private fun HomeScreenContent(
        navigateTo: (Screen) -> Unit,
        modifier: Modifier = Modifier,
        menuItems: List<ListItem>
) {
//    val postTop = posts[3]
//    val postsSimple = posts.subList(0, 2)
//    val postsPopular = posts.subList(2, 7)
//    val postsHistory = posts.subList(7, 10)
//
//    VerticalScroller(modifier = modifier) {
//        HomeScreenTopSection(postTop, navigateTo)
//        HomeScreenSimpleSection(postsSimple, navigateTo)
//        HomeScreenPopularSection(postsPopular, navigateTo)
//        HomeScreenHistorySection(postsHistory, navigateTo)
//    }

    VerticalScroller(modifier = Modifier.padding(horizontal = 16.dp)) {
        menuItems.forEach { item ->
            HomeScreenSection(item)
        }
    }


//    LazyColumnItems(menuItems, modifier = modifier.padding(horizontal = defaultSpacerSize)) { item ->
//        HomeScreenSection(item)
//    }

}

@Composable
private fun HomeScreenSection(item: ListItem) {
    val context = ContextAmbient.current
    Surface(elevation = 2.dp) {
        Row(verticalGravity = Alignment.CenterVertically,
             modifier = Modifier.clickable(onClick = {
            Toast.makeText(
                    context,
                    "You click on ${item.text}",
                    Toast.LENGTH_SHORT
            ).show()
        }).fillMaxWidth().preferredHeight(56.dp)) {
            item.icon?.takeIf { it != -1 }?.apply {
                Image(
                        asset = vectorResource(item.icon),
                        modifier = Modifier.preferredSize(40.dp),
                        colorFilter = ColorFilter.tint(contentColor()),
                        contentScale = ContentScale.Fit
                )
            }
            Spacer(Modifier.preferredWidth(8.dp))
            Text(text = item.text!!, style = MaterialTheme.typography.body1)
        }
    }
}


@Preview("Drawer contents")
@Composable
fun PreviewHomeScreenContent() {
    ThemedPreview {
        HomeScreenContent(
                navigateTo = { },
                modifier = Modifier,
                menuItems = getMenuItems()
        )
    }
}

@Preview("Drawer contents dark theme")
@Composable
fun PreviewHomeScreenContentDark() {
    ThemedPreview(darkTheme = true) {
        HomeScreenContent(
                navigateTo = { },
                modifier = Modifier,
                menuItems = getMenuItems()
        )
    }
}
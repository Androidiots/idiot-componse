package com.androidiots.compose.ui.home

import android.content.Context
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.*
import androidx.ui.foundation.*
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Assessment
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.androidiots.compose.R
import com.androidiots.compose.data.ListItem
import com.androidiots.compose.data.SubItem
import com.androidiots.compose.data.getMenuItems
import com.androidiots.compose.ui.AppDrawer
import com.androidiots.compose.ui.Screen
import com.androidiots.compose.ui.ThemedPreview
import com.androidiots.compose.ui.getNavigationScreen

@Composable
fun NewHomeScreen(
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
                NewHomeScreenContent(navigateTo, modifier, menuItems)
            }
    )
}

@Composable
private fun NewHomeScreenContent(
        navigateTo: (Screen) -> Unit,
        modifier: Modifier = Modifier,
        menuItems: List<ListItem>
) {

    VerticalScroller(modifier = modifier.padding(horizontal = 16.dp)) {
        menuItems.forEach { item ->
            NewHomeScreenSection(item, navigateTo)
        }
    }

}

@Composable
private fun NewHomeScreenSection(item: ListItem, navigateTo: (Screen) -> Unit) {
    val context = ContextAmbient.current
    Column {
        HeaderSection(item.text, item.icon)
        BodySection(item.subItems, context, navigateTo)
    }

}

@Composable
private fun BodySection(subItems: List<SubItem>?, context: Context, navigateTo: (Screen) -> Unit) {
    subItems?.forEach { item ->
        Row(verticalGravity = Alignment.CenterVertically,
            modifier =  Modifier.fillMaxWidth()
                    .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                    .clickable(onClick = {
//                        Toast.makeText(
//                                context,
//                                "You click on ${item.text}",
//                                Toast.LENGTH_SHORT
//                            ).show()
                        navigateTo(getNavigationScreen(item.id))
                        })
        ) {
            Image(
                    asset = Icons.Filled.Assessment,
                    modifier = Modifier.preferredSize(20.dp),
                    colorFilter = ColorFilter.tint(contentColor()),
                    contentScale = ContentScale.Fit
            )
            Spacer(Modifier.preferredWidth(8.dp))
            Text(text = item.text!!, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
private fun HeaderSection(text: String?, icon: Int) {
    Row(verticalGravity = Alignment.CenterVertically) {
        icon?.takeIf { it != -1 }?.apply {
            Image(
                    asset = vectorResource(icon),
                    modifier = Modifier.preferredSize(40.dp),
                    colorFilter = ColorFilter.tint(contentColor()),
                    contentScale = ContentScale.Fit
            )
        }
        Spacer(Modifier.preferredWidth(8.dp))
        Text(text = text!!, style = MaterialTheme.typography.body1)
    }
}


@Preview("Drawer contents")
@Composable
fun PreviewNewHomeScreenContent() {
    ThemedPreview {
        NewHomeScreenContent(
                navigateTo = { },
                modifier = Modifier,
                menuItems = getMenuItems()
        )
    }
}
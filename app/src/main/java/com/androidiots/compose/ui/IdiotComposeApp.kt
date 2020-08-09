package com.androidiots.compose.ui

import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.material.TextButton
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Home
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.androidiots.compose.R
import com.androidiots.compose.ui.activity.BasicGridScreen
import com.androidiots.compose.ui.activity.BasicListScreen
import com.androidiots.compose.ui.home.NewHomeScreen

@Composable
fun IdiotComposeApp(
    navigationViewModel: NavigationViewModel
) {
    IdiotcomposeTheme {
        AppContent(
            navigationViewModel = navigationViewModel
        )
    }
}

@Composable
private fun AppContent(
    navigationViewModel: NavigationViewModel
) {
    Crossfade(navigationViewModel.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.Home -> NewHomeScreen(
                    navigateTo = navigationViewModel::navigateTo)
                is Screen.BasicList -> BasicListScreen(
                        onBack = { navigationViewModel.onBack() }
                )
                is Screen.BasicGrid -> BasicGridScreen(
                        onBack = { navigationViewModel.onBack() }
                )
            }
        }
    }
}

@Composable
fun AppDrawer(
    navigateTo: (Screen) -> Unit,
    currentScreen: Screen,
    closeDrawer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.preferredHeight(24.dp))
        IdiotComposeLogo(Modifier.padding(16.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(
            icon = Icons.Filled.Home,
            label = "Home",
            isSelected = currentScreen == Screen.Home,
            action = {
                navigateTo(Screen.Home)
                closeDrawer()
            }
        )
        DrawerButton(
                icon = Icons.Filled.Home,
                label = "Rate this App",
                isSelected = currentScreen == Screen.RateApp,
                action = {
                    navigateTo(Screen.RateApp)
                    closeDrawer()
                }
        )
        DrawerButton(
                icon = Icons.Filled.Home,
                label = "About",
                isSelected = currentScreen == Screen.About,
                action = {
                    navigateTo(Screen.About)
                    closeDrawer()
                }
        )
    }
}

@Composable
private fun IdiotComposeLogo(modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.Start,
            verticalGravity = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
        Image(
            asset = imageResource(R.drawable.jetpack_logo),
//            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                modifier = modifier
                        .preferredSize(40.dp, 40.dp)
                        .clip(MaterialTheme.shapes.small)
        )
        Spacer(Modifier.preferredWidth(8.dp))
        Text(
                text = "Idiot-Compose",
                style = ThemeTypography.h5,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun DrawerButton(
    icon: VectorAsset,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val imageAlpha = if (isSelected) {
        1f
    } else {
        0.6f
    }
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalGravity = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    asset = icon,
                    colorFilter = ColorFilter.tint(textIconColor),
                    alpha = imageAlpha
                )
                Spacer(Modifier.preferredWidth(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview("Drawer contents")
@Composable
fun PreviewIdiotComposeApp() {
    ThemedPreview {
        AppDrawer(
            navigateTo = { },
            currentScreen = Screen.Home,
            closeDrawer = { }
        )
    }
}

@Preview("Drawer contents dark theme")
@Composable
fun PreviewIdiotComposeDark() {
    ThemedPreview(darkTheme = true) {
        AppDrawer(
            navigateTo = { },
            currentScreen = Screen.Home,
            closeDrawer = { }
        )
    }
}

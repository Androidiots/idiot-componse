package com.androidiots.compose.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.androidiots.compose.ui.Screen.Home
import com.androidiots.compose.ui.Screen.BasicList
import com.androidiots.compose.ui.Screen.BasicGrid
import com.androidiots.compose.ui.ScreenName.*
import com.androidiots.compose.utils.getMutableStateOf

enum class ScreenName { HOME, RATE_APP, ABOUT, BASIC_LIST, BASIC_GRID }

sealed class Screen(val id: ScreenName) {
    object Home : Screen(HOME)
    object RateApp : Screen(RATE_APP)
    object About : Screen(ABOUT)
    object BasicList : Screen(BASIC_LIST)
    object BasicGrid : Screen(BASIC_GRID)
}

private const val SIS_SCREEN = "sis_screen"
private const val SIS_NAME = "screen_name"

private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name).also {

    }
}

private fun Bundle.toScreen(): Screen {
    val screenName = ScreenName.valueOf(getStringOrThrow(
        SIS_NAME
    ))
    return when (screenName) {
        HOME -> Home
        RATE_APP -> Home
        ABOUT -> Home
        BASIC_LIST -> BasicList
        BASIC_GRID -> BasicGrid
    }
}

private fun Bundle.getStringOrThrow(key: String) =
    requireNotNull(getString(key)) { "Missing key '$key' in $this" }

class NavigationViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SIS_SCREEN,
        default = Home,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )
        private set // limit the writes to only inside this class.

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Home
        currentScreen = Home
        return wasHandled
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}

fun getNavigationScreen(id: Int): Screen {
    return when (id) {
        101 -> BasicList
        201 -> BasicGrid
        else -> BasicList
    }
}
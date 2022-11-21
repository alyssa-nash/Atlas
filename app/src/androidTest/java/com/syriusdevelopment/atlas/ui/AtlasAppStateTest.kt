/*
 * Copyright (C) 2022, Alyssa Nash <nash.alyssab@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.syriusdevelopment.atlas.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AtlasAppStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // Subject under test.
    private lateinit var state: AtlasAppState

    @Test
    fun appState_currentDestinationChanges_onNewNav() = runTest {
        var currentDestination: String? = null
        composeTestRule.setContent {
            val navController = rememberTestNavController()
            state = remember(navController) {
                AtlasAppState(
                    navController = navController,
                )
            }

            currentDestination = state.currentDestination?.route
            LaunchedEffect(Unit) {
                navController.setCurrentDestination("b")
            }
        }

        assertEquals("b", currentDestination)
    }

    @Test
    fun appState_confirmDestinations() = runTest {
        composeTestRule.setContent {
            state = rememberAppState()
        }

        assertEquals(4, state.topLevelDestinations.size)
        assertTrue(state.topLevelDestinations[0].name.contains("home", true))
        assertTrue(state.topLevelDestinations[1].name.contains("activities", true))
        assertTrue(state.topLevelDestinations[2].name.contains("health", true))
        assertTrue(state.topLevelDestinations[3].name.contains("settings", true))
    }

    @Composable
    private fun rememberTestNavController(): TestNavHostController {
        val context = LocalContext.current
        val navController = remember {
            TestNavHostController(context).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
                graph = createGraph(startDestination = "a") {
                    composable("a") { }
                    composable("b") { }
                    composable("c") { }
                }
            }
        }
        return navController
    }
}

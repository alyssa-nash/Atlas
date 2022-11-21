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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.syriusdevelopment.atlas.ui.activities.ActivitiesScreen
import com.syriusdevelopment.atlas.ui.design.components.Route
import com.syriusdevelopment.atlas.ui.design.components.TopLevelDestinations
import com.syriusdevelopment.atlas.ui.health.HealthScreen
import com.syriusdevelopment.atlas.ui.home.HomeScreen
import com.syriusdevelopment.atlas.ui.settings.SettingsScreen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AtlasAppState {
    return remember(navController) {
        AtlasAppState(navController)
    }
}

@Stable
class AtlasAppState(
    val navController: NavHostController,
) {
    val topLevelDestinations: ImmutableList<TopLevelDestinations> =
        persistentListOf<TopLevelDestinations>().addAll(TopLevelDestinations.values().toList())

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param destination: The destination the app needs to navigate to.
     */
    fun navigateTo(destination: TopLevelDestinations) {
        navController.navigate(destination.route.id) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun AtlasNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.HOME.id,
        modifier
    ) {
        composable(Route.HOME.id) {
            HomeScreen()
        }
        composable(Route.ACTIVITIES.id) {
            ActivitiesScreen()
        }
        composable(Route.HEALTH.id) {
            HealthScreen()
        }
        composable(Route.SETTINGS.id) {
            SettingsScreen()
        }
    }
}

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

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syriusdevelopment.atlas.R
import com.syriusdevelopment.atlas.ui.design.components.AtlasBottomBar
import com.syriusdevelopment.atlas.ui.design.components.AtlasTopAppBar
import com.syriusdevelopment.atlas.ui.theme.AtlasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtlasApp(
    modifier: Modifier = Modifier,
    appState: AtlasAppState = rememberAppState()
) {
    Scaffold(
        topBar = { AtlasTopAppBar(titleRes = R.string.app_name) },
        bottomBar = {
            AtlasBottomBar(
                destinations = appState.topLevelDestinations,
                currentDestination = appState.currentDestination,
                onNavigateToDestination = appState::navigateTo
            )
        },
        modifier = modifier
    ) { padding ->
        AtlasNavHost(
            navHostController = appState.navController,
            modifier = Modifier
                .padding(padding)
        )
    }
}

@Preview
@Composable
fun AtlasAppPreview() {
    AtlasApp()
}

@Preview
@Composable
fun AtlasAppDMPreview() {
    AtlasTheme(darkTheme = true) {
        AtlasApp()
    }
}

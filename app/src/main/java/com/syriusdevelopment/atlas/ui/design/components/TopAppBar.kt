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
package com.syriusdevelopment.atlas.ui.design.components

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.syriusdevelopment.atlas.R
import com.syriusdevelopment.atlas.ui.theme.AtlasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtlasTopAppBar(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        colors = colors,
        modifier = modifier,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.dog),
                    contentDescription = "Dog"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Top App Bar")
@Composable
fun TopAppBarDMPreview() {
    AtlasTheme(darkTheme = true) {
        AtlasTopAppBar(
            titleRes = R.string.app_name
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Top App Bar")
@Composable
fun TopAppBarLMPreview() {
    AtlasTopAppBar(
        titleRes = R.string.app_name
    )
}

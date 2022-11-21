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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.syriusdevelopment.atlas.R

/**
 * Material icons are [ImageVector]s, custom icons are drawable resource IDs.
 */
object AtlasIcons {
    val Home = Icons.Outlined.Home
    val HomeFilled = Icons.Filled.Home
    const val Award = R.drawable.award
    const val AwardFilled = R.drawable.award_filled
    const val Health = R.drawable.health
    const val HealthFilled = R.drawable.health_filled
    val Settings = Icons.Outlined.Settings
    val SettingsFilled = Icons.Filled.Settings
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}

enum class Route(
    val id: String
) {
    HOME("home"),
    ACTIVITIES("activities"),
    HEALTH("health"),
    SETTINGS("settings")
}

enum class TopLevelDestinations(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    @StringRes val label: Int,
    val route: Route
) {
    HOME(
        selectedIcon = Icon.ImageVectorIcon(AtlasIcons.HomeFilled),
        unselectedIcon = Icon.ImageVectorIcon(AtlasIcons.Home),
        label = R.string.home,
        route = Route.HOME
    ),
    ACTIVITIES(
        selectedIcon = Icon.DrawableResourceIcon(AtlasIcons.AwardFilled),
        unselectedIcon = Icon.DrawableResourceIcon(AtlasIcons.Award),
        label = R.string.activities,
        route = Route.ACTIVITIES
    ),
    HEALTH(
        selectedIcon = Icon.DrawableResourceIcon(AtlasIcons.HealthFilled),
        unselectedIcon = Icon.DrawableResourceIcon(AtlasIcons.Health),
        label = R.string.health,
        route = Route.HEALTH
    ),
    SETTINGS(
        selectedIcon = Icon.ImageVectorIcon(AtlasIcons.SettingsFilled),
        unselectedIcon = Icon.ImageVectorIcon(AtlasIcons.Settings),
        label = R.string.settings,
        route = Route.SETTINGS
    )
}

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
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kover.plugin)
    alias(libs.plugins.spotless.plugin)
    alias(libs.plugins.detekt.plugin)
}

subprojects {
    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
    extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint(rootProject.libs.versions.ktlint.get())
                .userData(mapOf("android" to "true"))
            licenseHeaderFile(rootProject.file("lint/copyrights/copyright.kt"))
        }
        kotlinGradle {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            ktlint(rootProject.libs.versions.ktlint.get())
                .userData(mapOf("android" to "true"))
          licenseHeaderFile(rootProject.file("lint/copyrights/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
            licenseHeaderFile(rootProject.file("lint/copyrights/copyright.xml"), "(<[^!?])")
        }
    }

    apply<io.gitlab.arturbosch.detekt.DetektPlugin>()
    detekt {
        buildUponDefaultConfig = true
        config = files(rootProject.file("detekt.yml"))
    }
    dependencies {
        detektPlugins(rootProject.libs.detekt.compose.rules)
    }

    apply<kotlinx.kover.KoverPlugin>()
}

koverMerged {
    enable()
    filters {
        classes {
            includes += "*.ViewModel.*"
        }
    }

    verify {
        rule {
            bound {
                minValue = 80
                counter = kotlinx.kover.api.CounterType.LINE
                valueType = kotlinx.kover.api.VerificationValueType.COVERED_PERCENTAGE
            }
        }
    }
}

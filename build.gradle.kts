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
        }
        kotlinGradle {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            ktlint(rootProject.libs.versions.ktlint.get())
                .userData(mapOf("android" to "true"))
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
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

plugins {
    id("kmp-configuration")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.serialization.json)
                implementation(libs.datetime)
            }
        }
    }
}
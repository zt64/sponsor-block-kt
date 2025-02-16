plugins {
    id("kmp-configuration")
    alias(libs.plugins.kotlin.serialization)
}

description = "Kotlin multiplatform client for SponsorBlock"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core)

                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.contentNegotiation)
                implementation(libs.ktor.serialization)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
                implementation(libs.ktor.test)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }

        nativeTest {
            dependencies {
                implementation(libs.ktor.client.curl)
            }
        }

        jsTest {
            dependencies {
                implementation(libs.ktor.client.js)
            }
        }
    }
}
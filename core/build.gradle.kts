import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    id("kmp-configuration")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    // Once Ktor also supports wasm, move to kmp-configuration plugin
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.serialization.json)
                implementation(libs.datetime)
            }
        }
    }
}
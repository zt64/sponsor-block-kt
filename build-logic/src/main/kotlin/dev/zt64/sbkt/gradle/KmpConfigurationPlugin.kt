package dev.zt64.sbkt.gradle

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsSubTargetDsl
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import java.util.*

@Suppress("unused")
private fun <T> Property<T>.assign(value: T) = set(value)

private val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

class KmpConfigurationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        configureKmp(target)
        configurePublishing(target)
        configureMaintenance(target)
    }

    @OptIn(ExperimentalWasmDsl::class)
    private fun configureKmp(target: Project) {
        target.apply(plugin = "org.jetbrains.kotlin.multiplatform")
        target.extensions.configure<KotlinMultiplatformExtension> {
            explicitApi()
            jvmToolchain(17)

            jvm()

            fun KotlinJsTargetDsl.configureSubTargets() {
                fun KotlinJsSubTargetDsl.extendTimeout() {
                    testTask {
                        useMocha {
                            timeout = "10s"
                        }
                    }
                }

                nodejs {
                    extendTimeout()
                }

                browser {
                    extendTimeout()
                }
            }

            js {
                configureSubTargets()
            }

            wasmJs {
                configureSubTargets()
            }

            apple()

            sourceSets.apply {
                commonMain {
                    dependencies {
                    }
                }
            }
        }
    }

    private fun configurePublishing(target: Project) {
        target.apply(plugin = "com.vanniktech.maven.publish")

        target.extensions.configure<MavenPublishBaseExtension> {
            coordinates(target.group.toString(), target.name, target.version.toString())
            publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
            signAllPublications()

            val path = "zt64/sponsorblock-kt"

            pom {
                name = "sponsorblock-kt"
                description = "Kotlin Multiplatform library for SponsorBlock API"
                inceptionYear = "2023"
                url = "https://github.com/$path"

                licenses {
                    license {
                        name = "GPL v3.0"
                        url = "https://www.gnu.org/licenses/gpl-3.0.html"
                    }
                }

                developers {
                    developer {
                        id = "zt64"
                        name = "zt64"
                        url = "https://zt64.dev"
                    }
                }

                scm {
                    url = "https://github.com/$path"
                    connection = "scm:git:github.com/$path.git"
                    developerConnection = "scm:git:ssh://github.com/$path.git"
                }
            }
        }
    }

    private fun KotlinMultiplatformExtension.apple(configure: KotlinNativeTarget.() -> Unit = {}) {
        val isMacOs = System.getProperty("os.name").lowercase(Locale.getDefault()).contains("mac")

        if (!isMacOs) return

        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
            macosX64(),
            macosArm64(),
            tvosX64(),
            tvosArm64(),
            tvosSimulatorArm64(),
            watchosArm32(),
            watchosArm64(),
            watchosSimulatorArm64()
        ).forEach(configure)
    }

    private fun configureMaintenance(target: Project) {
        target.apply {
            plugin("org.jlleitschuh.gradle.ktlint")
            plugin("org.jetbrains.kotlinx.binary-compatibility-validator")
        }

        target.configure<KtlintExtension> {
            version = target.libs.findVersion("ktlint").get().toString()
        }
    }
}
import com.android.build.gradle.internal.utils.isComposeCompilerPluginApplied
import com.eferraz.pokedex.bundles
import com.eferraz.pokedex.libraries
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugins
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class LibraryKoinPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.plugins.ksp)

            extensions.configure<KspExtension> {
                arg("KOIN_CONFIG_CHECK", "true")
                arg("KOIN_LOG_TIMES", "true")
                arg("KOIN_DEFAULT_MODULE", "false")
            }

            extensions.configure<KotlinMultiplatformExtension> {

                sourceSets {

                    commonMain {

                        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin/org/koin/ksp/generated")

                        dependencies {

                            implementation(project.dependencies.platform(libs.libraries.koin_bom))
                            implementation(libs.bundles.koin_common)

                            if (isComposeCompilerPluginApplied(project))
                                implementation(libs.bundles.koin_common_compose)
                        }
                    }

                    commonTest {
                        dependencies {
                            implementation(libs.bundles.koin_common_test)
                        }
                    }
                }
            }

            dependencies {
                add("kspCommonMainMetadata", libs.bundles.koin_common_compiler)
            }

            // Issue fix https://github.com/InsertKoinIO/koin/issues/2174
            tasks.named { it.startsWith("ksp") && it != "kspCommonMainKotlinMetadata" }.configureEach {
                dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
            }
        }
    }
}
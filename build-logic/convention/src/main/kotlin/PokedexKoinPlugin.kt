import com.android.build.gradle.internal.utils.isComposeCompilerPluginApplied
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
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

internal class PokedexKoinPlugin : Plugin<Project> {

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

                        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")

                        dependencies {

                            implementation(project.dependencies.platform(libs.libraries.koin_bom))
                            implementation(libs.libraries.koin_core)

                            if (isComposeCompilerPluginApplied(project)) {
                                implementation(libs.libraries.koin_compose)
                                implementation(libs.libraries.koin_compose_viewmodel)
                                implementation(libs.libraries.koin_compose_viewmodel_navigation)
                            }

                            api(libs.libraries.koin_annotations)
                        }
                    }

                    commonTest {
                        dependencies {
                            implementation(libs.libraries.koin_test)
//                            implementation(libs.libraries.koin_test_junit4)
                        }
                    }
                }
            }

            dependencies {
                add("kspCommonMainMetadata", libs.libraries.koin_ksp_compiler)
            }

            tasks.withType(KotlinCompilationTask::class.java).configureEach {
                if (name != "kspCommonMainKotlinMetadata") {
                    dependsOn("kspCommonMainKotlinMetadata")
                }
            }
        }
    }
}
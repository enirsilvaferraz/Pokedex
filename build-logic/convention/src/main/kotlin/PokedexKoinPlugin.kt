import com.eferraz.pokedex.isComposeModule
import com.eferraz.pokedex.libs
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

class PokedexKoinPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.findPlugin("ksp").get().get().pluginId)

            extensions.configure<KspExtension> {
                arg("KOIN_CONFIG_CHECK", "true")
                arg("KOIN_LOG_TIMES", "true")
            }

            extensions.configure<KotlinMultiplatformExtension> {

                sourceSets {

                    commonMain {

                        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")

                        dependencies {

                            implementation(project.dependencies.platform(libs.findLibrary("koin-bom").get()))
                            implementation(libs.findLibrary("koin-core").get())

                            if (isComposeModule()) {
                                implementation(libs.findLibrary("koin-compose").get())
                                implementation(libs.findLibrary("koin-compose-viewmodel").get())
                                implementation(libs.findLibrary("koin-compose-viewmodel-navigation").get())
                            }

                            api(libs.findLibrary("koin-annotations").get())
                        }
                    }
                }
            }

            dependencies {
                add("kspCommonMainMetadata", libs.findLibrary("koin-ksp-compiler").get())
            }

            tasks.withType(KotlinCompilationTask::class.java).configureEach {
                if (name != "kspCommonMainKotlinMetadata") {
                    dependsOn("kspCommonMainKotlinMetadata")
                }
            }
        }
    }
}
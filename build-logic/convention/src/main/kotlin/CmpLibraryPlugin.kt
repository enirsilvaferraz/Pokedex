import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class CmpLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.plugins.kotlin_multiplatform)
            apply(plugin = libs.plugins.compose_multiplatform)
            apply(plugin = libs.plugins.compose_compiler)

            val compose = extensions.getByType<ComposeExtension>().dependencies

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain.dependencies {
                        implementation(compose.runtime)
                        implementation(compose.foundation)
                        implementation(compose.material3)
                        implementation(compose.materialIconsExtended)
                        implementation(compose.ui)
                        implementation(compose.uiUtil)
                        implementation(compose.components.resources)
                        implementation(compose.components.uiToolingPreview)
                    }

                    commonTest.dependencies {

                        @OptIn(ExperimentalComposeLibrary::class)
                        implementation(compose.uiTest)
                    }

                    androidMain.dependencies {
                        implementation(libs.findLibrary("androidx-activity-compose").get())
                    }
                }
            }
        }
    }
}
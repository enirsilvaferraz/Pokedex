import com.eferraz.pokedex.libraries
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.extensions.stdlib.capitalized
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal abstract class KmpProjectPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.plugins.kotlin_multiplatform)

            extensions.configure<KotlinMultiplatformExtension> {

                explicitApi()

                compilerOptions {
                    // Common compiler options applied to all Kotlin source sets
                    freeCompilerArgs.add("-Xexpect-actual-classes")
                }

                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = project.name.capitalized() + "Kit"
                    }
                }

                sourceSets {

                    commonMain {
                        dependencies {
                            implementation(libs.libraries.kotlin_stdlib)
                        }
                    }

                    commonTest {
                        dependencies {
                            implementation(libs.libraries.kotlin_test)
                        }
                    }
                }
            }
        }
    }
}
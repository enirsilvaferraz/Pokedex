import com.eferraz.pokedex.isRoomModule
import com.eferraz.pokedex.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal abstract class KmpProjectPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.findPlugin("kotlin-multiplatform").get().get().pluginId)

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
                        baseName = "${project.name}Kit"
                        isStatic = true
                        if (isRoomModule()) linkerOpts.add("-lsqlite3")
                    }
                }

                sourceSets {

                    commonMain {
                        dependencies {
                            implementation(libs.findLibrary("kotlin-stdlib").get())
                        }
                    }

                    commonTest {
                        dependencies {
                            implementation(libs.findLibrary("kotlin-test").get())
                        }
                    }
                }
            }
        }
    }
}
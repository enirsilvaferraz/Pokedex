import com.android.build.api.dsl.androidLibrary
import com.eferraz.pokedex.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class KmpProjectPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.findPlugin("kotlin-multiplatform").get().get().pluginId)
            apply(plugin = libs.findPlugin("android-kotlin-multiplatform-library").get().get().pluginId)

            extensions.configure<KotlinMultiplatformExtension> {

                explicitApi()

                androidLibrary {
                    namespace = "$NAMESPACE.${project.name}"
                    compileSdk = libs.findVersion("android.targetSdk").get().requiredVersion.toInt()
                    minSdk = libs.findVersion("android.minSdk").get().requiredVersion.toInt()
                }

                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = "${project.name}Kit"
                    }
                }

                sourceSets {

                    commonMain {
                        dependencies {
                            implementation(libs.findLibrary("kotlin-stdlib").get())
                        }
                    }

                    commonTest  {
                        dependencies {
                            implementation(libs.findLibrary("kotlin-test").get())
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val NAMESPACE = "com.eferraz.pokedex"
    }
}
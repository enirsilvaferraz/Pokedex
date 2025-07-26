import androidx.room.gradle.RoomExtension
import com.eferraz.pokedex.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class PokedexRoomPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.findPlugin("ksp").get().get().pluginId)
            apply(plugin = libs.findPlugin("room").get().get().pluginId)

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain {
                        dependencies {
                            implementation(libs.findLibrary("room-runtime").get())
                            implementation(libs.findLibrary("sqlite-bundled").get())
                        }
                    }
                }
            }

            dependencies {
                listOf("kspAndroid", "kspIosSimulatorArm64", "kspIosX64", "kspIosArm64").forEach {
                    add(it, libs.findLibrary("room-compiler").get())
                }
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }
        }
    }
}
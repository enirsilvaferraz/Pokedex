import androidx.room.gradle.RoomExtension
import com.eferraz.pokedex.libraries
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugins
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

            apply(plugin = libs.plugins.ksp)
            apply(plugin = libs.plugins.room)

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain {
                        dependencies {
                            implementation(libs.libraries.room_runtime)
                            implementation(libs.libraries.sqlite_bundled)
                        }
                    }
                }
            }

            dependencies {
                listOf("kspAndroid", "kspIosSimulatorArm64", "kspIosX64", "kspIosArm64").forEach {
                    add(it, libs.libraries.room_compiler)
                }
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }
        }
    }
}
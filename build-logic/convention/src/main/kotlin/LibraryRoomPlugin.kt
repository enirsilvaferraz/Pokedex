import androidx.room.gradle.RoomExtension
import com.eferraz.pokedex.bundles
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class LibraryRoomPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.plugins.ksp)
            apply(plugin = libs.plugins.room)

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain {
                        dependencies {
                            implementation(libs.bundles.room_common)
                        }
                    }
                }
            }

            dependencies {
                listOf(
                    "kspAndroid",
                    "kspIosSimulatorArm64",
                    "kspIosX64",
                    "kspIosArm64"
                ).forEach {
                    add(it, libs.bundles.room_common_compiler)
                }
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }
        }
    }
}
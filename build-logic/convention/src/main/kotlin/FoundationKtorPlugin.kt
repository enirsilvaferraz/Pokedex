import com.eferraz.pokedex.libraries
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class FoundationKtorPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.plugins.kotlin_serialization)

            extensions.configure<KotlinMultiplatformExtension> {

                sourceSets {

                    commonMain {
                        dependencies {

                            implementation(libs.libraries.kotlinx_coroutines_core)

                            implementation(libs.libraries.ktor_client_core) // TODO colocar em um bundle do libs.version.toml
                            implementation(libs.libraries.ktor_client_encoding)
                            implementation(libs.libraries.ktor_client_logging)
                            implementation(libs.libraries.ktor_serialization_kotlinx_json)
                            implementation(libs.libraries.ktor_client_content_negotiation)
                            implementation(libs.libraries.kermit)
                        }
                    }

                    androidMain {
                        dependencies {
                            implementation(libs.libraries.ktor_client_okhttp)
                        }
                    }

                    iosMain {
                        dependencies {
                            implementation(libs.libraries.ktor_client_darwin)
                        }
                    }
                }
            }
        }
    }
}
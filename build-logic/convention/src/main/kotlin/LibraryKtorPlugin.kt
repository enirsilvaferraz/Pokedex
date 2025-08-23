import com.eferraz.pokedex.bundles
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class LibraryKtorPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.plugins.kotlin_serialization)

            extensions.configure<KotlinMultiplatformExtension> {

                sourceSets {

                    commonMain {
                        dependencies {
                            implementation(libs.bundles.ktor_common)
                        }
                    }

                    androidMain {
                        dependencies {
                            implementation(libs.bundles.ktor_android)
                        }
                    }

                    iosMain {
                        dependencies {
                            implementation(libs.bundles.ktor_ios)
                        }
                    }
                }
            }
        }
    }
}
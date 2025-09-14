import com.eferraz.pokedex.CatalogDefinitions.Bundles.KTOR_ANDROID
import com.eferraz.pokedex.CatalogDefinitions.Bundles.KTOR_COMMON
import com.eferraz.pokedex.CatalogDefinitions.Bundles.KTOR_IOS
import com.eferraz.pokedex.CatalogDefinitions.Plugins.KOTLIN_SERIALIZATION
import com.eferraz.pokedex.bundle
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class LibraryKtorPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.plugin(KOTLIN_SERIALIZATION))

            extensions.configure<KotlinMultiplatformExtension> {

                sourceSets {

                    commonMain {
                        dependencies {
                            implementation(libs.bundle(KTOR_COMMON))
                        }
                    }

                    androidMain {
                        dependencies {
                            implementation(libs.bundle(KTOR_ANDROID))
                        }
                    }

                    iosMain {
                        dependencies {
                            implementation(libs.bundle(KTOR_IOS))
                        }
                    }
                }
            }
        }
    }
}
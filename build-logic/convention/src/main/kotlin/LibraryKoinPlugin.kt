import com.android.build.gradle.internal.utils.isComposeCompilerPluginApplied
import com.eferraz.pokedex.CatalogDefinitions.Bundles.KOIN_COMMON
import com.eferraz.pokedex.CatalogDefinitions.Bundles.KOIN_COMMON_COMPILER
import com.eferraz.pokedex.CatalogDefinitions.Bundles.KOIN_COMMON_COMPOSE
import com.eferraz.pokedex.CatalogDefinitions.Bundles.KOIN_COMMON_TEST
import com.eferraz.pokedex.CatalogDefinitions.Libraries.KOIN_BOM
import com.eferraz.pokedex.CatalogDefinitions.Plugins.KSP
import com.eferraz.pokedex.bundle
import com.eferraz.pokedex.library
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class LibraryKoinPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.plugin(KSP))

            extensions.configure<KotlinMultiplatformExtension> {

                sourceSets {

                    commonMain {

                        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin/org/koin/ksp/generated")

                        dependencies {

                            implementation(project.dependencies.platform(libs.library(KOIN_BOM)))
                            implementation(libs.bundle(KOIN_COMMON))

                            if (isComposeCompilerPluginApplied(project))
                                implementation(libs.bundle(KOIN_COMMON_COMPOSE))
                        }
                    }

                    commonTest {
                        dependencies {
                            implementation(libs.bundle(KOIN_COMMON_TEST))
                        }
                    }
                }
            }

            dependencies {
                add("kspCommonMainMetadata", libs.bundle(KOIN_COMMON_COMPILER))
            }

            // Issue fix https://github.com/InsertKoinIO/koin/issues/2174
            tasks.named { it.startsWith("ksp") && it != "kspCommonMainKotlinMetadata" }.configureEach {
                dependsOn(tasks.named("kspCommonMainKotlinMetadata"))
            }
        }
    }
}
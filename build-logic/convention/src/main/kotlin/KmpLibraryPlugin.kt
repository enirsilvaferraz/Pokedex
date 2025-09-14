import com.android.build.api.dsl.androidLibrary
import com.eferraz.pokedex.AbstractKmpProjectPlugin
import com.eferraz.pokedex.CatalogDefinitions.Plugins.KOTLIN_MULTIPLATFORM_LIBRARY
import com.eferraz.pokedex.CatalogDefinitions.Versions.MIN_SDK
import com.eferraz.pokedex.CatalogDefinitions.Versions.TARGET_SDK
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugin
import com.eferraz.pokedex.version
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class KmpLibraryPlugin : AbstractKmpProjectPlugin() {

    override fun apply(target: Project) {
        super.apply(target)

        with(target) {

            apply(plugin = libs.plugin(KOTLIN_MULTIPLATFORM_LIBRARY))

            extensions.configure<KotlinMultiplatformExtension> {

                @Suppress("UnstableApiUsage")
                androidLibrary {
                    // namespace = // Aplicado posteriormente
                    compileSdk = libs.version(TARGET_SDK)
                    minSdk = libs.version(MIN_SDK)
                }
            }
        }
    }
}
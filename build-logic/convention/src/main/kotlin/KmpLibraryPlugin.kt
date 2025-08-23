import com.android.build.api.dsl.androidLibrary
import com.eferraz.pokedex.AbstractKmpProjectPlugin
import com.eferraz.pokedex.ProjectConstants.NAMESPACE
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugins
import com.eferraz.pokedex.versions
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class KmpLibraryPlugin : AbstractKmpProjectPlugin() {

    override fun apply(target: Project) {
        super.apply(target)

        with(target) {

            apply(plugin = libs.plugins.kotlin_multiplatform_library)

            extensions.configure<KotlinMultiplatformExtension> {

                @Suppress("UnstableApiUsage")
                androidLibrary {
                    namespace = "$NAMESPACE.${project.name}"
                    compileSdk = libs.versions.targetSdk
                    minSdk = libs.versions.minSdk
                }
            }
        }
    }
}
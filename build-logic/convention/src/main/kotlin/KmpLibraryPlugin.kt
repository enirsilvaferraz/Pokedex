import com.android.build.api.dsl.androidLibrary
import com.eferraz.pokedex.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class KmpLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {

            apply(plugin = libs.findPlugin("android-kotlin-multiplatform-library").get().get().pluginId)

            extensions.configure<KotlinMultiplatformExtension> {

                androidLibrary {
                    namespace = "$NAMESPACE.${project.name}"
                    compileSdk = libs.findVersion("android.targetSdk").get().requiredVersion.toInt()
                    minSdk = libs.findVersion("android.minSdk").get().requiredVersion.toInt()
                }
            }
        }
    }

    companion object {
        private const val NAMESPACE = "com.eferraz.pokedex"
    }
}
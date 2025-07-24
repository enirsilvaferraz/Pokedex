import com.android.build.api.dsl.androidLibrary
import com.eferraz.pokedex.ProjectConstants.NAMESPACE
import com.eferraz.pokedex.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class KmpLibraryPlugin : KmpProjectPlugin() {

    override fun apply(target: Project) {
        super.apply(target)

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
}
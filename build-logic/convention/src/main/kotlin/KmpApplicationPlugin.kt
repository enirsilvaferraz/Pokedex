import com.android.build.api.dsl.ApplicationExtension
import com.eferraz.pokedex.ProjectConstants.NAMESPACE
import com.eferraz.pokedex.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class KmpApplicationPlugin : KmpProjectPlugin() {

    override fun apply(target: Project) {
        super.apply(target)

        with(target) {

            apply(plugin = libs.findPlugin("android.application").get().get().pluginId)

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    @OptIn(ExperimentalKotlinGradlePluginApi::class)
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_11)
                    }
                }
            }

            extensions.configure<ApplicationExtension> {

                namespace = NAMESPACE
                compileSdk = libs.findVersion("android.compileSdk").get().requiredVersion.toInt()

                defaultConfig {
                    applicationId = NAMESPACE
                    minSdk = libs.findVersion("android.minSdk").get().requiredVersion.toInt()
                    targetSdk = libs.findVersion("android.targetSdk").get().requiredVersion.toInt()
                    versionCode = 1
                    versionName = "1.0"
                }

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
            }
        }
    }
}
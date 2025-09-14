import com.android.build.api.dsl.ApplicationExtension
import com.eferraz.pokedex.AbstractKmpProjectPlugin
import com.eferraz.pokedex.libs
import com.eferraz.pokedex.plugins
import com.eferraz.pokedex.versions
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class KmpApplicationPlugin : AbstractKmpProjectPlugin() {

    override fun apply(target: Project) {
        super.apply(target)

        with(target) {

            apply(plugin = libs.plugins.android_application)

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    @OptIn(ExperimentalKotlinGradlePluginApi::class)
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_11)
                    }
                }
            }

            extensions.configure<ApplicationExtension> {

//                namespace = // aplicado posteriomente com a chamada do metodo setNamespace
                compileSdk = libs.versions.compileSdk

                defaultConfig {
//                    applicationId = // aplicado posteriomente com a chamada do metodo setNamespace
                    minSdk = libs.versions.minSdk
                    targetSdk = libs.versions.targetSdk
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
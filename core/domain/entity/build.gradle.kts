plugins {
    alias(libs.plugins.foundation.project)
    alias(libs.plugins.foundation.library.koin)
}

kotlin.android {
    namespace = "com.eferraz.entity"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies{
        val nav_version = "2.8.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply false
}
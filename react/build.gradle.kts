plugins {
    kotlin("js") version "1.4.30"
}

group = "com.redhat.kotlinjs"
version = "0.0.1"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

val reactVersion = "17.0.1-pre.148-kotlin-1.4.21"
val styledVersion = "5.2.1-pre.148-kotlin-1.4.21"

dependencies {
    implementation("org.jetbrains:kotlin-react:$reactVersion")
    implementation("org.jetbrains:kotlin-react-dom:$reactVersion")
    implementation("org.jetbrains:kotlin-styled:$styledVersion")
    implementation(npm("tailwindcss", "2.0.3"))
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
            binaries.executable()
        }
    }
}

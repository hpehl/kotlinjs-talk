plugins {
    kotlin("js") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
}

group = "com.redhat.kotlinjs"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.jfrog.org/artifactory/jfrog-dependencies")
    maven("https://dl.bintray.com/patternfly-kotlin/patternfly-fritz2")
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("io.ktor:ktor-client-core:1.5.1")
    implementation("io.ktor:ktor-client-serialization:1.5.1")
    implementation("dev.fritz2:core:0.8")
    implementation("org.patternfly:patternfly-fritz2:0.2.0")
    implementation(npm("@patternfly/patternfly", "4"))
    implementation(devNpm("file-loader", "6.2.0"))
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

plugins {
    kotlin("js") version "1.4.30"
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
    implementation("dev.fritz2:core:0.8")
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

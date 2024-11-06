

plugins {
    id("com.android.library")
    id("maven-publish")

}
android {
    namespace = "com.bemo.prayertime"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
afterEvaluate{
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "bemo.prayer.time"
                artifactId = "prayer"
                version = "1.0.8-alpha"
                from(project.components.findByName("java"))
                artifact("$buildDir/outputs/aar/PrayerTime-release.aar")

            }
        }
        repositories {
            maven {

                url = uri("https://maven.pkg.github.com/ibrahim4Hamdy/PrayerTime")
                name = "PrayerTime" // هذا يسمح بتحديد اسم المستودع
                credentials{

                    username = project.findProperty("mavenUsername") as String?
                    password = project.findProperty("mavenPassword") as String?

                }

            }

        }
    }


}


dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
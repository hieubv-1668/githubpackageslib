import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.hieubv.githubpackageslib"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.hieubv.githubpackageslib"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val githubPropertiesFile = rootProject.file("github.properties");
val githubProperties = Properties()
githubProperties.load(FileInputStream(githubPropertiesFile))

//subprojects {
//    apply(plugin = "maven-publish")
//    configure<PublishingExtension> {
//        repositories {
//            maven {
//                name = "GitHubPackages"
//                url = uri("https://maven.pkg.github.com/hieubv-1668/githubpackageslib")
//                credentials {
//                    username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
//                    password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
//                }
//            }
//        }
//        publications {
//            register<MavenPublication>("gpr") {
//                from(components["java"])
//            }
//        }
//    }
//}
////GitHub Authentication
//repositories {
//    maven {
//        name = "GitHubPackages"
//        /**  Configure path to the library hosted on GitHub Packages Registry
//         *  Replace UserID with package owner userID and REPOSITORY with the repository name
//         *  e.g. "https://maven.pkg.github.com/enefce/AndroidLibrary-GPR-KDSL"
//         */
////        url = uri("https://maven.pkg.github.com/UserID/REPOSITORY")
//        url = uri("https://maven.pkg.github.com/hieubv-1668/githubpackageslib")
//
//        credentials {
//            /**Create github.properties in root project folder file with gpr.usr=GITHUB_USER_ID  & gpr.key =PERSONAL_ACCESS_TOKEN**/
//            username = githubProperties["gpr.usr"] as String? ?: System.getenv("GPR_USER")
//            password = githubProperties["gpr.key"] as String? ?: System.getenv("GPR_API_KEY")
//        }
//    }
//}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.hieubv.core"
    compileSdk = 33
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    defaultConfig {
        aarMetadata {
            minCompileSdk = 24
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

/**Create github.properties in root project folder file with gpr.usr=GITHUB_USER_ID  & gpr.key=PERSONAL_ACCESS_TOKEN**/
val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

fun getVersionName(): String {
    return "1.0.2" // Replace with version Name
}

fun getArtificatId(): String {
    return "core" // Replace with library name ID
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.hieubv.githubpackageslib"
                artifactId = getArtificatId()
                version = getVersionName()
                artifact("$buildDir/outputs/aar/${getArtificatId()}-release.aar")
            }
        }

// Step 1: generate aar file to maven local
//        register<MavenPublication>("release"){
//            run {
//                groupId = "com.hieubv.githubpackageslib"
//                artifactId = getArtificatId()
//                version = getVersionName()
//                afterEvaluate {
//                    from(components["release"])
//                }
//            }
//        }
    }

    repositories {
        maven {
            name = "core"
            url = uri("$buildDir/repo")
        }
    }


    repositories {
        maven {
            name = "GithubPackages"
            /** Configure path of your package repository on Github
             ** Replace GITHUB_USERID with your/organisation Github userID
             ** and REPOSITORY with the repository name on GitHub: "https://maven.pkg.github.com/GITHUB_USERID/REPOSITORY"
             */
            url = uri("https://maven.pkg.github.com/hieubv-1668/githubpackageslib")
            credentials {
                username = githubProperties["gpr.usr"] as? (String?) ?: System.getenv("GPR_USER")
                password = githubProperties["gpr.key"] as? (String?) ?: System.getenv("GPR_API_KEY")
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
}

android {
    namespace = "com.mtrilogic.spannabletextlibrary"
    compileSdk = 34

    defaultConfig {
        aarMetadata {
            minCompileSdk = 24
        }
        minSdk = 24

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

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

// Nuestro archivo básico de publicación en repositorios
/*publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.mtrilogic"
            artifactId = "spannabletextlibrary"
            version = "0.0.0"

            afterEvaluate {
                from(components["release"])
                groupId = "com.mtrilogic"
                artifactId = "spannabletextlibrary"
                version = "0.0.0"
            }
        }
    }
    repositories {
        mavenLocal()
        maven {
            name = "Repositories"
            url = uri("file:///D:/MTRI/Android/Repositories")
        }
    }
}*/

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["release"])
                groupId = "com.mtrilogic"
                artifactId = "spannabletextlibrary"
                version = "0.0.0"
            }
        }
    }
}

// Esto genera un archivo llamado "spannabletextlibrary.zip"
// localizado en la carpeta build/distributions del módulo
/*tasks.register<Zip>("generateMTRIAndroidRepositorie") {
    val publishTask = tasks.named(
        "publishReleasePublicationToRepositoriesRepository",
        PublishToMavenRepository::class.java)
    from(publishTask.map {
        it.repository.url
    })
    into("spannabletextlibrary")
    archiveFileName.set("spannabletextlibrary.zip")
}*/

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
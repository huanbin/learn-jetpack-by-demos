pluginManagement {
    repositories {
        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
        google()
        mavenCentral()
    }
}
rootProject.name = "LearnJetpackByDemos"
include(":app")
include(":demo-module-nav")
include(":login-module-for-hilt-multimodule")

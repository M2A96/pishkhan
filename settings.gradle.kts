pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Mobilebank"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Mobilebank"
include(":app")

val rootDirectories = listOf(
    "core",
    "feature",
)
rootDirectories
    .map { File(rootDir, it) }
    .flatMap { findModules(it, 3) }
    .onEach {
        include(it)
    }

fun findModules(directory: File, depth: Int): List<String> =
    directory.walk()
        .maxDepth(depth)
        .filter { it.isDirectory && File(it, "build.gradle.kts").exists() }
        .map { it.toRelativeString(directory.parentFile).replace(File.separator, ":") }
        .toList()

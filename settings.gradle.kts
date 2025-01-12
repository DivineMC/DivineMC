import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

if (!file(".git").exists()) {
    val errorText = """
        
        =====================[ ERROR ]=====================
         The DivineMC project directory is not a properly cloned Git repository.
         
         In order to build DivineMC from source you must clone
         the DivineMC repository using Git, not download a code
         zip from GitHub.
         
         Built Gale jars are available for download at
         https://github.com/DivineMC/DivineMC/actions
         
         See https://github.com/PaperMC/Paper/blob/main/CONTRIBUTING.md
         for further information on building and modifying Paper forks.
        ===================================================
    """.trimIndent()
    error(errorText)
}

rootProject.name = "DivineMC"

for (name in listOf("divinemc-api", "divinemc-server")) {
    val projName = name.lowercase(Locale.ENGLISH)
    include(projName)
    findProject(":$projName")!!.projectDir = file(name)
}
plugins {
    java
}

val spigotVersion: String by extra

group = "dev.tonimatas.fastregions"
version = "$spigotVersion-${getVersionMetadata()}"

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:$spigotVersion-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withSourcesJar()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<ProcessResources> {
    inputs.property("version", version)

    filesMatching("plugin.yml") {
        expand("version" to version)
    }
}

fun getVersionMetadata(): String {
    val buildId = System.getenv("GITHUB_RUN_NUMBER")

    if (buildId != null) {
        return "build.$buildId"
    }

    return "unknown"
}

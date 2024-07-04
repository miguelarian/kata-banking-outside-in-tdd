plugins {
    id("java")
}

group = "com.miguelvela"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs = listOf(
        "-XX:+EnableDynamicAgentLoading", // If using a serviceability tool
        //"-Djdk.instrument.traceUsage"    // If not using a serviceability tool
    )
}
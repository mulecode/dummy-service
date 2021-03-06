plugins {
    java
    idea
    jacoco
    id("maven-publish")
    id("io.freefair.lombok") version "5.2.1"
    id("org.springframework.boot") version "2.5.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

group = "uk.co.mulecode"
//version = "1.0.0"
version = file("./version.txt").readText().trim()



dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-parent:2.5.3")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //MVC
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/mulecode/dummy-service")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(tasks.bootJar.get())
        }
    }
}

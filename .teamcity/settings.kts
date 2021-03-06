import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.DslContext
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.project
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.version

version = "2018.1"

project {
    buildType {
        id("Build")
        name = "Build"

        vcs { root(DslContext.settingsRoot) }

        steps {
            gradle { tasks = "clean build" }
        }

        artifactRules = "build/libs/app*.jar"

        triggers { vcs {  } }

        cleanup {
            artifacts(days = 2)
            history(days = 3)
        }
    }
}

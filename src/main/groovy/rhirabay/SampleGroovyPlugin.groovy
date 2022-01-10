package rhirabay

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.compile.JavaCompile

class SampleGroovyPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def springBootVersion = project.plugins.findPlugin('org.springframework.boot').class.package.implementationVersion
        def springBootMajorVersion = springBootVersion.split('\\.')[0]
        def springBootMinorVersion = springBootVersion.split('\\.')[1]

        new SampleGroovySubPlugin().apply(project)

        project.task('detectWarnings') {
            dependsOn('clean')
            dependsOn(project.tasks.withType(JavaCompile))

            doLast {
                println("spring boot version: ${springBootVersion}")
                println("spring boot major version: ${springBootMajorVersion}")
                println("spring boot minor version: ${springBootMinorVersion}")
            }
        }

        if (project.plugins.hasPlugin("java-library")) {
            project.tasks.bootJar {
                enabled = false
            }
            project.jar {
                enabled = true
                archiveClassifier = ""
            }
        } else {
            project.tasks.bootJar {
                enabled = true
            }
            project.tasks.jar {
                enabled = false
            }
        }

        project.tasks.withType(JavaCompile) {
            options.compilerArgs << '-Xlint:deprecation'
        }
    }
}

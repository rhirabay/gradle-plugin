package rhirabay

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.api.tasks.compile.JavaCompile

class SampleGroovySubPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task('subTask') {
            doLast {
                println("this is sub plugin")
            }
        }
    }
}

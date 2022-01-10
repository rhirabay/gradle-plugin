package rhirabay;

import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.compile.JavaCompile;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class SampleJavaPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.task("myFirstTask")
                .doLast(t -> {
                    System.out.println("my first task");
                    System.out.println("jar enabled? " + project.getTasks().getByName("jar").property("enabled"));
                    System.out.println("boot plugin id " + project.getPluginManager().findPlugin("org.springframework.boot").getId());

//                    var versions = project.getExtensions()
//                            .findByType(DependencyManagementExtension.class);
//                    var versions = SpringBootApplication.class.getPackage().getImplementationVersion();
//                    var versions = project.getExtensions()
//                            .findByType(DependencyManagementExtension.class);
//                    System.out.println("spring dependencies: " + versions);
                });

        if (project.getPlugins().hasPlugin("java-library")) {
            project.getTasks().getByName("bootJar").setEnabled(false);
            project.getTasks().getByName("jar").setProperty("archiveClassifier", "");
        } else {
            project.getTasks().getByName("jar").setEnabled(false);
        }

        for (JavaCompile compile: project.getTasks().withType(JavaCompile.class)) {
            var args = compile.getOptions().getCompilerArgs();
            args.add("-Xlint:deprecation");
        }
    }
}

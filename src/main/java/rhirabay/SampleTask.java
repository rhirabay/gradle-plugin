package rhirabay;

import org.gradle.api.Plugin;
import org.gradle.api.internal.project.ProjectInternal;

public class SampleTask implements Plugin<ProjectInternal> {

    @Override
    public void apply(ProjectInternal projectInternal) {
        System.out.println();
    }
}

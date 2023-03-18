package xyz.fyirou.Gradle;

import org.gradle.api.Project;
import xyz.fyirou.Gradle.extension.FyirouExtension;
import xyz.fyirou.Gradle.util.LibrariesUtil;
import xyz.fyirou.Gradle.util.UrlUtil;

public class Plugin implements org.gradle.api.Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getLogger().lifecycle("***************Fyirou Gradle***************");
        project.getLogger().lifecycle("2022/3/18 创建");
        project.getLogger().lifecycle("作者：Frish2021");
        project.getLogger().lifecycle("这个是用来提供给开发人员的Gradle插件");
        project.getLogger().lifecycle("*******************************************");

        project.getExtensions().create("minecraft", FyirouExtension.class);

        project.afterEvaluate(after -> {

            after.getRepositories().maven(mavenArtifactRepository -> {
                mavenArtifactRepository.setName("minecraft");
                mavenArtifactRepository.setUrl(UrlUtil.GAME_LIBRARIES);
            });

            after.getRepositories().mavenCentral();
            after.getRepositories().mavenLocal();

            after.getPlugins().apply("java");
            after.getPlugins().apply("idea");

            FyirouExtension version = project.getExtensions().getByType(FyirouExtension.class);
            LibrariesUtil.getLibraries(version).forEach(library -> after.getDependencies().add("implementation", library));
        });
    }
}

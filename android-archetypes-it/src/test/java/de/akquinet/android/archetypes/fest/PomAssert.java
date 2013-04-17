package de.akquinet.android.archetypes.fest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.assertj.core.api.AbstractAssert;

import junit.framework.AssertionFailedError;

import de.akquinet.android.archetypes.Helper;

public class PomAssert extends AbstractAssert<PomAssert, Pom> {

    String fileContent;

    protected PomAssert(Pom pomFile) {
	super(pomFile, PomAssert.class);
	try {
	    fileContent = Helper.readFileToString(pomFile.getFileName());
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public PomAssert isAndroidMavenPluginDeclared() {
	assertThat(fileContent).contains(
		"<artifactId>android-maven-plugin</artifactId>")
		.overridingErrorMessage(
			"android-maven-plugin not declared in "
				+ actual.getFileName());
	return this;
    }

    public PomAssert isPlatformDeclared(int level) {
	String stringToFind = "<platform>" + level + "</platform>";
	assertThat(fileContent).contains(stringToFind).overridingErrorMessage(
		"platform " + level + " not declared in "
			+ actual.getFileName());
	return this;
    }

    public PomAssert isAvdDeclared(String name) {
	String stringToFind = "<avd>" + name + "</avd>";
	assertThat(fileContent).contains(stringToFind).overridingErrorMessage(
		"avd " + name + " not declared in " + actual.getFileName());
	return this;
    }

    public PomAssert hasText(String target) {
	assertThat(fileContent).contains(target).overridingErrorMessage(
		target + "hasn't found in " + actual.getFileName());
	return this;
    }
}

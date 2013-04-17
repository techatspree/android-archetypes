package de.akquinet.android.archetypes.fest;

import org.assertj.core.api.Assertions;

public class AndroidArchetypeAssertions extends Assertions {

    public static AndroidManifestAssert assertThat(
	    AndroidManifest androidManifest) {
	return new AndroidManifestAssert(androidManifest);
    }

    public static PomAssert assertThat(Pom pomFile) {
	return new PomAssert(pomFile);
    }

}

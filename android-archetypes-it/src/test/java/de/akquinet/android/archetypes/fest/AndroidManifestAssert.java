package de.akquinet.android.archetypes.fest;

import java.io.IOException;

import org.assertj.core.api.AbstractAssert;
import static org.assertj.core.api.Assertions.assertThat;

import de.akquinet.android.archetypes.Helper;

/***
 * This class perform some basic assertion about the AndroidManifest.xml file.
 * If and where the Android sdklib wil be deployt to maven central this class
 * should use it for perform assertions.
 * 
 * @author rciovati
 */
public class AndroidManifestAssert extends
	AbstractAssert<AndroidManifestAssert, AndroidManifest> {

    String fileContent;

    protected AndroidManifestAssert(AndroidManifest androidManifest) {
	super(androidManifest, AndroidManifestAssert.class);
	try {
	    fileContent = Helper
		    .readFileToString(androidManifest.getFileName());
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public AndroidManifestAssert hasActivity(String activityName) {
	String stringToFind = "<activity android:name=\"" + activityName
		+ "\" >";
	assertThat(fileContent).contains(stringToFind).overridingErrorMessage(
		"Activity " + activityName + " not found");
	return this;
    }

    public AndroidManifestAssert hasService(String serviceName) {
	String stringToFind = "<service android:name=\"" + serviceName
		+ "\" />";
	assertThat(fileContent).contains(stringToFind).overridingErrorMessage(
		"Service " + serviceName + " not found");
	return this;
    }

    public AndroidManifestAssert hasPackage(String packageName) {
	String stringToFind = "package=\"" + packageName + "\"";
	assertThat(fileContent).contains(stringToFind).overridingErrorMessage(
		"Package " + packageName + " not found");
	return this;
    }

    public AndroidManifestAssert hasTargetVersion(int version) {
	String stringToFind = "android:targetSdkVersion=\"" + version + "\"";
	assertThat(fileContent).contains(stringToFind).overridingErrorMessage(
		"targetSdkVersion " + version + " not found");
	return this;
    }

    public AndroidManifestAssert isUsingAndroidTestRunner() {
	String stringToFind = "<uses-library android:name=\"android.test.runner\" />";
	assertThat(fileContent).contains(stringToFind).overridingErrorMessage(
		stringToFind + " not found");
	return this;
    }

    public AndroidManifestAssert hasTargetPackage(String packageName) {
	String stringToFind = "<instrumentation android:targetPackage=\""
		+ packageName + "\"";
	assertThat(fileContent).contains(stringToFind).overridingErrorMessage(
		stringToFind + " not found");
	return this;
    }

    public AndroidManifestAssert hasPermission(String permission) {
	String stringToFind = "<uses-permission android:name=\"" + permission
		+ "\" />";
	assertThat(fileContent).contains(stringToFind).overridingErrorMessage(
		stringToFind + " not found");
	return this;
    }

}

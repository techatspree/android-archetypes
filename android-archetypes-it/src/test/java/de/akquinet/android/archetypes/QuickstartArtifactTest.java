package de.akquinet.android.archetypes;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.junit.Before;
import org.junit.Test;


public class QuickstartArtifactTest {

    @Before
    public void setUp() {
        if (System.getProperty("archetype.version") == null) {
            System.setProperty("archetype.version", "1.5.0-SNAPSHOT");
        }
    }

    /**
     * Checks the quick-start archetype with no parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testQuickStartDefault() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("quickstart-default");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-quickstart");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test");
        verifier.assertFilePresent("android-test/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/res/values/strings.xml");
        verifier.assertFilePresent("android-test/res/layout/main.xml");
        verifier.assertFilePresent("android-test/assets");
        verifier.assertFilePresent("android-test/src/main/java/android/archetypes/test/HelloAndroidActivity.java");


        Helper.assertContains(new File("target/it/quickstart-default/android-test/pom.xml"), "<artifactId>maven-android-plugin</artifactId>");
        Helper.assertContains(new File("target/it/quickstart-default/android-test/pom.xml"), "<platform>7</platform>");
        Helper.assertContains(new File("target/it/quickstart-default/android-test/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/quickstart-default/android-test/AndroidManifest.xml"), "package=\"android.archetypes.test\"");
    }

    /**
     * Checks the quick-start archetype with the <tt>platform</tt> parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testQuickStartWithPlatform() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("quickstart-with-platform");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-quickstart");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=8");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test");
        verifier.assertFilePresent("android-test/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/res/values/strings.xml");
        verifier.assertFilePresent("android-test/res/layout/main.xml");
        verifier.assertFilePresent("android-test/assets");
        verifier.assertFilePresent("android-test/src/main/java/android/archetypes/test/HelloAndroidActivity.java");


        Helper.assertContains(new File("target/it/quickstart-with-platform/android-test/pom.xml"), "<artifactId>maven-android-plugin</artifactId>");
        Helper.assertContains(new File("target/it/quickstart-with-platform/android-test/pom.xml"), "<platform>8</platform>");
        Helper.assertContains(new File("target/it/quickstart-with-platform/android-test/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/quickstart-with-platform/android-test/AndroidManifest.xml"), "package=\"android.archetypes.test\"");
    }

    /**
     * Checks the quick-start archetype with the <tt>platform</tt> and <tt>package</tt> parameters.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testQuickStartWithPlatformAndPackage() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("quickstart-with-platform-and-package");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-quickstart");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=8");
        cli.add("-Dpackage=foo");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test");
        verifier.assertFilePresent("android-test/AndroidManifest.xml");
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/res/values/strings.xml");
        verifier.assertFilePresent("android-test/res/layout/main.xml");
        verifier.assertFilePresent("android-test/assets");
        verifier.assertFilePresent("android-test/src/main/java/foo/HelloAndroidActivity.java");


        Helper.assertContains(new File("target/it/quickstart-with-platform-and-package/android-test/pom.xml"), "<artifactId>maven-android-plugin</artifactId>");
        Helper.assertContains(new File("target/it/quickstart-with-platform-and-package/android-test/pom.xml"), "<platform>8</platform>");
        Helper.assertContains(new File("target/it/quickstart-with-platform-and-package/android-test/AndroidManifest.xml"), "<activity android:name=\".HelloAndroidActivity\">");
        Helper.assertContains(new File("target/it/quickstart-with-platform-and-package/android-test/AndroidManifest.xml"), "package=\"foo\"");
    }


}

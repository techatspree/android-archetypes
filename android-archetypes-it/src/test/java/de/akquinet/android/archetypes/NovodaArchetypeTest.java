/*
 * Copyright 2010 akquinet
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.akquinet.android.archetypes;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.junit.Before;
import org.junit.Test;


public class NovodaArchetypeTest {

    private static final String APP = "app";
    private static final String CORE = "core";
    private static final String INSTRUMENTATION = "instrumentation";
    private static final String ANDROID_TEST = "android-test/";
    private static final String NOVODA_DEFAULT_PATH = "target/it/novoda-default/android-test/";
    private static final String NOVODA_PLATFORM_PACKAGE_PATH = "target/it/novoda-with-platform-and-package/android-test/";

    @Before
    public void setUp() {
        Helper.defineArchetypeVersion();
    }

    /**
     * Checks the novoda archetype with no parameter.
     *
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestDefault() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("novoda-default");

        Verifier verifier = new Verifier(root.getAbsolutePath(), false);
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-novoda");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent(ANDROID_TEST + APP);
        verifier.assertFilePresent(ANDROID_TEST + CORE);
        verifier.assertFilePresent(ANDROID_TEST + INSTRUMENTATION);
        verifier.assertFilePresent(ANDROID_TEST + ".gitignore");
        verifier.assertFilePresent(ANDROID_TEST + "pom.xml");
        verifier.assertFilePresent(ANDROID_TEST + "README.md");

        verifier.assertFilePresent(ANDROID_TEST + APP + "/pom.xml");
        verifier.assertFilePresent(ANDROID_TEST + CORE + "/pom.xml");
        verifier.assertFilePresent(ANDROID_TEST + INSTRUMENTATION + "/pom.xml");

        verifier.assertFilePresent(ANDROID_TEST + APP + "/AndroidManifest.xml");
        verifier.assertFilePresent(ANDROID_TEST + INSTRUMENTATION + "/AndroidManifest.xml");

        verifier.assertFilePresent(ANDROID_TEST + APP + "/res/values/strings.xml");
        verifier.assertFilePresent(ANDROID_TEST + APP + "/res/values/style_base.xml");
        verifier.assertFilePresent(ANDROID_TEST + APP + "/res/layout/main.xml");
        verifier.assertFilePresent(ANDROID_TEST + APP + "/assets");
        verifier.assertFilePresent(ANDROID_TEST + APP + "/src/android/archetypes/test/MainActivity.java");
        verifier.assertFilePresent(ANDROID_TEST + INSTRUMENTATION + "/src/android/archetypes/test/test/MainActivityTest.java");

        /* Parent pom tests*/
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + "/pom.xml"), "<module>app</module>");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + "/pom.xml"), "<module>core</module>");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + "/pom.xml"), "<module>instrumentation</module>");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + "/pom.xml"), "<android.version>4.1.1.4</android.version>");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + "/pom.xml"), "<android-test.version>2.3.3</android-test.version>");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + "/pom.xml"), "<android.sdk.platform>16</android.sdk.platform>");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + "/pom.xml"), "<android.maven.plugin.version>3.3.2</android.maven.plugin.version>");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + "/pom.xml"), "<support-library.version>r7</support-library.version>");

        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + APP + "/AndroidManifest.xml"), "<activity android:name=\".MainActivity\">");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + APP + "/AndroidManifest.xml"), "package=\"android.archetypes.test\"");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + INSTRUMENTATION + "/AndroidManifest.xml"), "<uses-library android:name=\"android.test.runner\"/>");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + INSTRUMENTATION + "/AndroidManifest.xml"), "<instrumentation");   //TODO replace it with parsing process so can browse elements, see children and their params
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + INSTRUMENTATION + "/AndroidManifest.xml"), "android:targetPackage=\"android.archetypes.test\"");

        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + INSTRUMENTATION + "/src/android/archetypes/test/test/MainActivityTest.java"), "super(MainActivity.class);");


        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + APP + "/default.properties"), "target=android-16");
        Helper.assertContains(new File(NOVODA_DEFAULT_PATH + INSTRUMENTATION + "/project.properties"), "target=android-16");
    }

    /**
     * Checks the novoda archetype with the <tt>platform</tt> and <tt>package</tt> parameters.
     *
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestWithPlatformAndPackage() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("novoda-with-platform-and-package");

        Verifier verifier = new Verifier(root.getAbsolutePath(), false);
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-novoda");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=7");
        cli.add("-Dpackage=foo");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create for package naming
        verifier.assertFilePresent(ANDROID_TEST + APP + "/src//foo/MainActivity.java");
        verifier.assertFilePresent(ANDROID_TEST + INSTRUMENTATION + "/src/foo/test/MainActivityTest.java");

        Helper.assertContains(new File(NOVODA_PLATFORM_PACKAGE_PATH + "pom.xml"), "2.1.2");
        //Parent pom check
        Helper.assertContains(new File(NOVODA_PLATFORM_PACKAGE_PATH + "/pom.xml"), "<android.version>2.1.2</android.version>");
        Helper.assertContains(new File(NOVODA_PLATFORM_PACKAGE_PATH + "/pom.xml"), "<android.sdk.platform>7</android.sdk.platform>");
        //Generated classes check
        Helper.assertContains(new File(NOVODA_PLATFORM_PACKAGE_PATH + APP + "/AndroidManifest.xml"), "<activity android:name=\".MainActivity\">");
        Helper.assertContains(new File(NOVODA_PLATFORM_PACKAGE_PATH + APP + "/AndroidManifest.xml"), "package=\"foo\"");
        Helper.assertContains(new File(NOVODA_PLATFORM_PACKAGE_PATH + INSTRUMENTATION + "/AndroidManifest.xml"), "<instrumentation");
        Helper.assertContains(new File(NOVODA_PLATFORM_PACKAGE_PATH + INSTRUMENTATION + "/AndroidManifest.xml"), "android:targetPackage=\"foo\"");
    }
}

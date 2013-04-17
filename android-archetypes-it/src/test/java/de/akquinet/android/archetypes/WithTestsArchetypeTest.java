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

import static de.akquinet.android.archetypes.fest.AndroidArchetypeAssertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.junit.Before;
import org.junit.Test;

import de.akquinet.android.archetypes.fest.AndroidManifest;
import de.akquinet.android.archetypes.fest.Pom;


public class WithTestsArchetypeTest {

    @Before
    public void setUp() {
        Helper.defineArchetypeVersion();
    }

    /**
     * Checks the with-test archetype with no parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestDefault() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-default");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");
        
        
        Pom mainPom = new Pom("target/it/with-test-default/android-test/pom.xml");

        assertThat(mainPom)
        	.isPlatformDeclared(16)
        	.isAndroidMavenPluginDeclared();
       
        //Checks about application
       
        Helper.checkAppFolderStructureAndFiles(verifier,"android-test/" + Constants.TEST_ARTIFACT_ID , Constants.TEST_GROUP_ID);
        
        Pom appPomFile = new Pom("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");

        assertThat(appPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest appManifest = new AndroidManifest("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        
        assertThat(appManifest)
        	.hasActivity(".HelloAndroidActivity")
        	.hasPackage("android.archetypes.test")
        	.hasTargetVersion(16);
        
        //Checks about instrumentation tests
        
        Pom itPomFile = new Pom("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        assertThat(itPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest itManifest = new AndroidManifest("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        
        assertThat(itManifest)
        	.hasTargetPackage("android.archetypes.test")
        	.isUsingAndroidTestRunner();

        //Misc checks
        
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java"), "super(HelloAndroidActivity.class);");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "/default.properties"), "target=android-16");
        Helper.assertContains(new File("target/it/with-test-default/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/default.properties"), "target=android-16");

    }

    /**
     * Checks the with-test archetype with the <tt>platform</tt> parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestWithPlatform() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-with-platform");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=8");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");

        Pom mainPom = new Pom("target/it/with-test-with-platform/android-test/pom.xml");

        assertThat(mainPom)
        	.isPlatformDeclared(8)
        	.isAndroidMavenPluginDeclared();
        
        //Checks about application
        
        Helper.checkAppFolderStructureAndFiles(verifier,"android-test/" + Constants.TEST_ARTIFACT_ID , Constants.TEST_GROUP_ID);
        
        Pom appPomFile = new Pom("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");

        assertThat(appPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest appManifest = new AndroidManifest("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        
        assertThat(appManifest)
        	.hasActivity(".HelloAndroidActivity")
        	.hasPackage("android.archetypes.test")
        	.hasTargetVersion(8);
        
        //Checks about instrumentation tests
        
        Pom itPomFile = new Pom("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        assertThat(itPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest itManifest = new AndroidManifest("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        
        assertThat(itManifest)
        	.hasTargetPackage("android.archetypes.test")
        	.isUsingAndroidTestRunner();

        //Misc checks
        
        Helper.assertContains(new File("target/it/with-test-with-platform/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java"), "super(HelloAndroidActivity.class);");

    }

    /**
     * Checks the with-test archetype with the <tt>platform</tt> and <tt>package</tt> parameters.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestWithPlatformAndPackage() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-with-platform-and-package");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
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


        // Check folder create.
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");
        
        Pom mainPom = new Pom("target/it/with-test-with-platform-and-package/android-test/pom.xml");

        assertThat(mainPom)
        	.isPlatformDeclared(7)
        	.isAndroidMavenPluginDeclared();

        //Checks about application
        
        Helper.checkAppFolderStructureAndFiles(verifier,"android-test/" + Constants.TEST_ARTIFACT_ID , "foo");
        
        Pom appPomFile = new Pom("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");

        assertThat(appPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest appManifest = new AndroidManifest("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        
        assertThat(appManifest)
        	.hasActivity(".HelloAndroidActivity")
        	.hasPackage("foo")
        	.hasTargetVersion(7);
        
        //Checks about instrumentation tests
        
        Pom itPomFile = new Pom("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        assertThat(itPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest itManifest = new AndroidManifest("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        
        assertThat(itManifest)
        	.hasTargetPackage("foo")
        	.isUsingAndroidTestRunner();

        //Misc checks
        
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/pom.xml"), "2.1.2");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "/default.properties"), "target=android-7");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/default.properties"), "target=android-7");
        Helper.assertContains(new File("target/it/with-test-with-platform-and-package/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/foo/test/HelloAndroidActivityTest.java"), "super(\"foo\", HelloAndroidActivity.class);");
    }


    /**
     * Checks the with-test archetype with the emulator parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestWithEmulator() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-emulator");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");
        cli.add("-Demulator=test");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");
        
        Pom mainPom = new Pom("target/it/with-test-emulator/android-test/pom.xml");

        assertThat(mainPom)
        	.isPlatformDeclared(16)
        	.isAvdDeclared("test")
        	.isAndroidMavenPluginDeclared();

        //Checks about application
        
        Helper.checkAppFolderStructureAndFiles(verifier,"android-test/" + Constants.TEST_ARTIFACT_ID , Constants.TEST_GROUP_ID);
        
        Pom appPomFile = new Pom("target/it/with-test-emulator/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");

        assertThat(appPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest appManifest = new AndroidManifest("target/it/with-test-emulator/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        
        assertThat(appManifest)
        	.hasActivity(".HelloAndroidActivity")
        	.hasPackage("android.archetypes.test")
        	.hasTargetVersion(16);
        
        //Checks about instrumentation tests
        
        Pom itPomFile = new Pom("target/it/with-test-emulator/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        assertThat(itPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest itManifest = new AndroidManifest("target/it/with-test-emulator/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        
        assertThat(itManifest)
        	.hasTargetPackage("android.archetypes.test")
        	.isUsingAndroidTestRunner();

        //Misc checks
        Helper.assertContains(new File("target/it/with-test-emulator/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java"), "super(HelloAndroidActivity.class);");
        Helper.assertContains(new File("target/it/with-test-emulator/android-test/" + Constants.TEST_ARTIFACT_ID + "/default.properties"), "target=android-16");
        Helper.assertContains(new File("target/it/with-test-emulator/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/default.properties"), "target=android-16");
    }

    /**
     * Checks the with-test archetype with the <tt>platform</tt> parameter bellow 7 for correct constructor.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testWithTestConstructorCorrectness() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("with-test-with-platform2");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-with-test");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-Dplatform=7");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");


        // Check folder create.
        verifier.assertFilePresent("android-test/pom.xml");
        verifier.assertFilePresent("android-test/" + Constants.TEST_ARTIFACT_ID + "-it");
        
        Pom mainPom = new Pom("target/it/with-test-with-platform2/android-test/pom.xml");

        assertThat(mainPom)
        	.isPlatformDeclared(7)
        	.isAndroidMavenPluginDeclared();

        //Checks about application
        
        Helper.checkAppFolderStructureAndFiles(verifier,"android-test/" + Constants.TEST_ARTIFACT_ID , Constants.TEST_GROUP_ID);
        
        Pom appPomFile = new Pom("target/it/with-test-with-platform2/android-test/" + Constants.TEST_ARTIFACT_ID + "/pom.xml");

        assertThat(appPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest appManifest = new AndroidManifest("target/it/with-test-with-platform2/android-test/" + Constants.TEST_ARTIFACT_ID + "/AndroidManifest.xml");
        
        assertThat(appManifest)
        	.hasActivity(".HelloAndroidActivity")
        	.hasPackage("android.archetypes.test")
        	.hasTargetVersion(7);
        
        //Checks about instrumentation tests
        
        Pom itPomFile = new Pom("target/it/with-test-with-platform2/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/pom.xml");

        assertThat(itPomFile)
        	.isAndroidMavenPluginDeclared();
        
        AndroidManifest itManifest = new AndroidManifest("target/it/with-test-with-platform2/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/AndroidManifest.xml");
        
        assertThat(itManifest)
        	.hasTargetPackage("android.archetypes.test")
        	.isUsingAndroidTestRunner();

        //Misc checks

        Helper.assertContains(new File("target/it/with-test-with-platform2/android-test/" + Constants.TEST_ARTIFACT_ID + "-it/src/main/java/android/archetypes/test/test/HelloAndroidActivityTest.java"), "super(\"android.archetypes.test\", HelloAndroidActivity.class);");

    }
}

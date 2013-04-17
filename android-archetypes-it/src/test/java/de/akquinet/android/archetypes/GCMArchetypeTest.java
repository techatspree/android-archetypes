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

import de.akquinet.android.archetypes.fest.AndroidManifest;
import de.akquinet.android.archetypes.fest.Pom;
import static de.akquinet.android.archetypes.fest.AndroidArchetypeAssertions.assertThat;

public class GCMArchetypeTest {
	
    @Before
    public void setUp() {
        Helper.defineArchetypeVersion();
    }

    /**
     * Checks the quick-start archetype with no parameter.
     * @throws IOException a file cannot be read
     * @throws VerificationException the maven launch failed
     */
    @Test
    public void testQuickStartDefault() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("gcm-default");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-gcm-quickstart");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");
        cli.add("-DsenderId=my-sender-id");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");

        // Check folder create.
        Helper.checkAppFolderStructureAndFiles(verifier, Constants.TEST_ARTIFACT_ID, Constants.TEST_GROUP_ID);
        
        Pom pomFile = new Pom("target/it/gcm-default/android-test/pom.xml");

        assertThat(pomFile)
        	.isAndroidMavenPluginDeclared()
        	.isPlatformDeclared(16);
        
        AndroidManifest manifest = new AndroidManifest("target/it/gcm-default/android-test/AndroidManifest.xml");
        
        assertThat(manifest)
        	.hasActivity(".HelloAndroidActivity")
        	.hasPackage("android.archetypes.test")
        	.hasService(".GCMIntentService")
        	.hasPermission("android.archetypes.test.permission.C2D_MESSAGE")
        	.hasTargetVersion(16);
        
        
        Helper.assertContains(new File("target/it/gcm-default/android-test/assets/gcmutils.properties"), "sender-id=my-sender-id");
        
        // Check that the Eclipse file is created (default.properties)
        Helper.assertContains(new File("target/it/gcm-default/android-test/default.properties"), "target=android-16");
    }

    /**
     * Checks the quick-start archetype with the <tt>platform</tt> parameter.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testQuickStartWithPlatform() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("gcm-with-platform");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);
        
        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-gcm-quickstart");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeRepository=local");
        cli.add("-Dplatform=8");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DsenderId=my-sender-id");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");

        // Check folder create.
        Helper.checkAppFolderStructureAndFiles(verifier, Constants.TEST_ARTIFACT_ID, Constants.TEST_GROUP_ID);
        
        
        Pom pomFile = new Pom("target/it/gcm-with-platform/android-test/pom.xml");

        assertThat(pomFile)
        	.isAndroidMavenPluginDeclared()
        	.isPlatformDeclared(8);
        	
        AndroidManifest manifest = new AndroidManifest("target/it/gcm-with-platform/android-test/AndroidManifest.xml");
        
        assertThat(manifest)
		.hasActivity(".HelloAndroidActivity")
		.hasPackage("android.archetypes.test")
		.hasService(".GCMIntentService")
		.hasPermission("android.archetypes.test.permission.C2D_MESSAGE")
		.hasTargetVersion(8);

        Helper.assertContains(new File("target/it/gcm-with-platform/android-test/assets/gcmutils.properties"), "sender-id=my-sender-id");
        
        // Check that the Eclipse file is created (default.properties)
        Helper.assertContains(new File("target/it/gcm-with-platform/android-test/default.properties"), "target=android-8");
    }

    /**
     * Checks the quick-start archetype with the <tt>platform</tt> and <tt>package</tt> parameters.
     * @throws VerificationException
     * @throws IOException
     */
    @Test
    public void testQuickStartWithPlatformAndPackage() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("gcm-with-platform-and-package");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-gcm-quickstart");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");
        cli.add("-Dplatform=8");
        cli.add("-Dpackage=foo");
        cli.add("-DsenderId=my-sender-id");

        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");

        // Check folder create and installed files
        Helper.checkAppFolderStructureAndFiles(verifier, Constants.TEST_ARTIFACT_ID, "foo");

        Pom pomFile = new Pom("target/it/gcm-with-platform-and-package/android-test/pom.xml");

        assertThat(pomFile)
        	.isAndroidMavenPluginDeclared()
        	.isPlatformDeclared(8);
        
        AndroidManifest manifest = new AndroidManifest("target/it/gcm-with-platform-and-package/android-test/AndroidManifest.xml");
        
        assertThat(manifest)
		.hasActivity(".HelloAndroidActivity")
		.hasPackage("foo")
		.hasService(".GCMIntentService")
		.hasPermission("foo.permission.C2D_MESSAGE")
		.hasTargetVersion(8);
        
        Helper.assertContains(new File("target/it/gcm-with-platform-and-package/android-test/assets/gcmutils.properties"), "sender-id=my-sender-id");
    }


    /**
     * Checks the quick-start archetype with the emulator parameter.
     * @throws IOException a file cannot be read
     * @throws VerificationException the maven launch failed
     */
    @Test
    public void testQuickStartWithEmulator() throws VerificationException, IOException {

        File root = Helper.prepareDirectory("gcm-default");

        Verifier verifier  = new Verifier( root.getAbsolutePath(), false );
        verifier.setAutoclean(false);

        verifier.displayStreamBuffers();

        @SuppressWarnings("unchecked")
        List<String> cli = verifier.getCliOptions();
        cli.add("-DarchetypeArtifactId=android-gcm-quickstart");
        cli.add("-DarchetypeGroupId=de.akquinet.android.archetypes");
        cli.add("-DarchetypeVersion=" + System.getProperty("archetype.version"));
        cli.add("-DgroupId=" + Constants.TEST_GROUP_ID);
        cli.add("-DartifactId=" + Constants.TEST_ARTIFACT_ID);
        cli.add("-DinteractiveMode=false");
        cli.add("-DarchetypeCatalog=local");
        cli.add("-DarchetypeRepository=local");
        cli.add("-Demulator=test");
        cli.add("-DsenderId=my-sender-id");
        
        verifier.executeGoal("org.apache.maven.plugins:maven-archetype-plugin:2.0:generate");

        // Check folder create.
        Helper.checkAppFolderStructureAndFiles(verifier, Constants.TEST_ARTIFACT_ID, Constants.TEST_GROUP_ID);
        
        Pom pomFile = new Pom("target/it/gcm-default/android-test/pom.xml");

        assertThat(pomFile)
        	.isAndroidMavenPluginDeclared()
        	.isAvdDeclared("test")
        	.isPlatformDeclared(16);
        
        AndroidManifest manifest = new AndroidManifest("target/it/gcm-default/android-test/AndroidManifest.xml");
        
        assertThat(manifest)
		.hasActivity(".HelloAndroidActivity")
		.hasPackage("android.archetypes.test")
		.hasService(".GCMIntentService")
		.hasPermission("android.archetypes.test.permission.C2D_MESSAGE")
		.hasTargetVersion(16);
        
        Helper.assertContains(new File("target/it/gcm-default/android-test/assets/gcmutils.properties"), "sender-id=my-sender-id");

        // Check that the Eclipse file is created (default.properties)
        Helper.assertContains(new File("target/it/gcm-default/android-test/default.properties"), "target=android-16");
    }


}

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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.maven.it.Verifier;
import org.junit.Assert;

public class Helper {

	public static void defineArchetypeVersion() {
		if (System.getProperty("archetype.version") == null) {
			System.setProperty("archetype.version", "1.0.10-SNAPSHOT");
		}
		System.out.println("archetype.version="
				+ System.getProperty("archetype.version"));
	}

	public static File prepareDirectory(String dir) {
		File file = new File("target/it/" + dir);
		if (file.exists()) {
			delete(file);
		}
		file.mkdirs();
		return file;
	}

	public static void delete(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else {
				File[] files = file.listFiles();
				for (File f : files) {
					delete(f);
				}
				file.delete();
			}
		}
	}

	public static final String readInputStream(InputStream is)
			throws IOException {
		StringBuffer acc = new StringBuffer();
		byte[] buf = new byte[1024];
		int len;
		while ((len = is.read(buf)) > 0) {
			for (int i = 0; i < len; i++) {
				acc.append((char) buf[i]);
			}
		}
		is.close();
		return acc.toString();

	}
	
	public static final String readFileToString(String fileName) throws IOException{
		FileInputStream fis = new FileInputStream(fileName);
		String out = readInputStream(fis);
		fis.close();
		return out;
	}
	

	public static void assertContains(String txt, String fragment) {
		if (txt.lastIndexOf(fragment) == -1) {
			Assert.fail(" The text does not contain " + fragment);
		}
	}

	public static void assertContains(File file, String fragment)
			throws IOException {
		FileInputStream fis = new FileInputStream(file);
		String out = readInputStream(fis);
		fis.close();
		assertContains(out, fragment);
	}

	public static void assertNotContains(String txt, String fragment) {
		if (txt.lastIndexOf(fragment) != -1) {
			Assert.fail(" The text does contain " + fragment);
		}
	}

	public static void checkAppFolderStructureAndFiles(Verifier verifier, String appArtifactDir,
			String packageName) {
		verifier.assertFilePresent(appArtifactDir);
		verifier.assertFilePresent(appArtifactDir + "/AndroidManifest.xml");
		verifier.assertFilePresent(appArtifactDir + "/pom.xml");
		verifier.assertFilePresent(appArtifactDir
				+ "/res/layout/activity_main.xml");
		verifier.assertFilePresent(appArtifactDir + "/res/menu/main.xml");
		verifier.assertFilePresent(appArtifactDir + "/res/values/strings.xml");
		verifier.assertFilePresent(appArtifactDir + "/res/values/dimens.xml");
		verifier.assertFilePresent(appArtifactDir + "/res/values/styles.xml");
		verifier.assertFilePresent(appArtifactDir
				+ "/res/values-sw600dp/dimens.xml");
		verifier.assertFilePresent(appArtifactDir
				+ "/res/values-sw720dp-land/dimens.xml");
		verifier.assertFilePresent(appArtifactDir
				+ "/res/values-v11/styles.xml");
		verifier.assertFilePresent(appArtifactDir
				+ "/res/values-v14/styles.xml");
		verifier.assertFilePresent(appArtifactDir + "/assets");
		verifier.assertFilePresent(appArtifactDir + "/src/main/java/"
				+ fromPackageToPath(packageName) + "/HelloAndroidActivity.java");
	}

	private static String fromPackageToPath(String packageName) {
		return packageName.replace(".", "/");
	}
}

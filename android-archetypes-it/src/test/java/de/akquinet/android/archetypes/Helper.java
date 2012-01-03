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
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;

public class Helper {
    
    public static void defineArchetypeVersion() {
        if (System.getProperty("archetype.version") == null) {
            System.setProperty("archetype.version", "1.0.7-SNAPSHOT");
        }
        System.out.println("archetype.version=" + System.getProperty("archetype.version"));
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

    public static final String readInputStream(InputStream is) throws IOException {
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

    public static void assertContains(String txt, String fragment) {
        if (txt.lastIndexOf(fragment) == -1) {
            Assert.fail(" The text does not contain " + fragment);
        }
    }

    public static void assertContains(File file, String fragment) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        String out = readInputStream(fis);
        fis.close();
        if (out.lastIndexOf(fragment) == -1) {
            Assert.fail(" The text does not contain " + fragment);
        }
    }

    public static void assertNotContains(String txt, String fragment) {
        if (txt.lastIndexOf(fragment) != -1) {
            Assert.fail(" The text does contain " + fragment);
        }
    }




}

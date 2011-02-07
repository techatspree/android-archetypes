package de.akquinet.android.archetypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;

public class Helper {

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

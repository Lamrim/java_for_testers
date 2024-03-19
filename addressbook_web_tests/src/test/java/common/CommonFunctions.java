package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class CommonFunctions {
    public static String randomString(int length) {
        StringBuilder result = new StringBuilder();
        var rnd = new Random();

        for (int i = 0; i < length; i++) {
            result.append((char) ('a' + rnd.nextInt(26)));
        }
        return result.toString();
    }

    public static String randomFile(String dir) {
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }
}

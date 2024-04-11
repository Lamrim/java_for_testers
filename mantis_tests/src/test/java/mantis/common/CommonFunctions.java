package mantis.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int length) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);

        var result = Stream.generate(randomNumbers)
                .limit(length)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());

        return result;
    }

    public static String randomFile(String dir) {
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }

    public static String extractUrl(String message, String url) {
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(message);
        if (matcher.find()) {
            url = message.substring(matcher.start(), matcher.end());
        }
        return url;
    }
}

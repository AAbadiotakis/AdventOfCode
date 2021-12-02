package style.hidden.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderUtil {
    static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    public static List<String> parseFile(String fileName) throws Exception {
        return Files.readAllLines(Paths.get(classLoader.getResource(fileName).toURI()));
    }

    public static String parseFileAsString(String fileName) throws Exception {
        return Files.readAllLines(Paths.get(classLoader.getResource(fileName).toURI())).stream()
                .collect(Collectors.joining("\n"));
    }
}

package style.hidden.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Runner {

    static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    final String path;
    final List<String> lines;

    protected List<String> parseFile(String fileName) throws Exception {
        return Files.readAllLines(Paths.get(classLoader.getResource(fileName).toURI()));
    }

    protected List<String> getLines() {
        return lines;
    }

    public Runner(String path) throws Exception {
        this.path = path;
        this.lines = parseFile(path);
    }

    public abstract void part1Code();

    public abstract void part2Code();

}

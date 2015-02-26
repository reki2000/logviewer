package com.github.reki2000.logviewer.loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoader implements LogLoader {
    private String file;

    public FileLoader(String file) {
        this.file = file;
    }

    public Iterable<String> load() throws IOException {
        return Files.readAllLines(Paths.get(file));
    }

    public String name() {
        return file;
    }

}

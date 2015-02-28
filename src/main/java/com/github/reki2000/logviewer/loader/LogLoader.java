package com.github.reki2000.logviewer.loader;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface LogLoader {

    default public Stream<String> stream() throws IOException {
        return StreamSupport.stream(load().spliterator(), false);
    }

    default public Iterable<String> load() throws IOException {
        return stream().collect(Collectors.toList());
    }

    public String name();
}

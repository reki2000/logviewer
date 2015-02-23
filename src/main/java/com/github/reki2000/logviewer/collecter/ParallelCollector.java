package com.github.reki2000.logviewer.collecter;

import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.parser.LogParser;
import com.github.reki2000.logviewer.model.LineView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class ParallelCollector implements LogCollector {
    private LogParser parser;
    private List<LogLoader> loader;

    public ParallelCollector(List<LogLoader> loader, LogParser parser) {
        this.parser = parser;
        this.loader = loader;
    }

    public Stream<LineView> lineViews() {
        return loader.stream()
            .parallel()
            .flatMap(loader -> {
                Stream<LineView> st = Stream.empty();
                try {
                    st = loader.stream()
                            .map(parser::parseLine)
                            .filter(v -> v != null);
                } catch (IOException e) {
                    // ignore;
                }
                return st;
            });
    }
}

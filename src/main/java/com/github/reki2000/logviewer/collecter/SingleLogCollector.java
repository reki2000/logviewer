package com.github.reki2000.logviewer.collecter;

import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.parser.LogParser;
import com.github.reki2000.logviewer.model.LineView;

import java.io.IOException;
import java.util.stream.Stream;

public class SingleLogCollector implements LogCollector {
    LogParser parser;
    LogLoader loader;

    public SingleLogCollector(LogLoader loader, LogParser parser) {
        this.parser = parser;
        this.loader = loader;
    }

    public Stream<LineView> lineViews() {
        try {
            return loader.stream()
                    .map( s -> parser.parseLine(s) )
                    .filter ( v -> v != null );
        } catch (IOException e) {
            // ignore
        }
        return Stream.empty();
    }

}

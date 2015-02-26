package com.github.reki2000.logviewer.collecter;

import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.parser.LogParser;
import com.github.reki2000.logviewer.model.LineView;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SingleLogCollector implements LogCollector {
    LogParser parser;
    LogLoader loader;

    public SingleLogCollector(LogLoader loader, LogParser parser) {
        this.parser = parser;
        this.loader = loader;
    }

    public CompletableFuture<Collection<LineView>> lineViews() {
        return CompletableFuture.supplyAsync(() -> {
            List<LineView> result = Collections.<LineView>emptyList();
            try {
                result = loader.stream()
                        .map(s -> parser.parseLine(s))
                        .filter(v -> v != null)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                // ignore
            }
            return result;
        });
    }

}

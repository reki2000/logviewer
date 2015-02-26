package com.github.reki2000.logviewer.collecter;

import com.github.reki2000.logviewer.model.LineView;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public interface LogCollector {

    default public CompletableFuture<Collection<LineView>> collectAsync() {
        return CompletableFuture
            .supplyAsync(() -> {
                try {
                    return collect();
                } catch (Exception e) {
                    return Collections.emptyList();
                }
            });
    }

    public Collection<LineView> collect() throws Exception;
}

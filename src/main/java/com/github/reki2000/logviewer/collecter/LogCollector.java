package com.github.reki2000.logviewer.collecter;

import com.github.reki2000.logviewer.model.LineView;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface LogCollector {
    public CompletableFuture<Collection<LineView>> lineViews();
}

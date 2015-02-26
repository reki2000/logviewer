package com.github.reki2000.logviewer.ui;

import com.github.reki2000.logviewer.collecter.SingleLogCollector;
import com.github.reki2000.logviewer.loader.FileLoader;
import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.loader.PipeLoader;
import com.github.reki2000.logviewer.parser.LogParser;
import com.github.reki2000.logviewer.parser.SampleLogParser;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Controller {
    final Main app;

    public Controller(Main app) {
        this.app = app;
        this.loaders = Arrays.asList(
                new PipeLoader("c:\\apps\\git\\bin\\cat.exe c:\\me\\git\\logviewer\\resource\\test.log"),
                new PipeLoader("c:\\apps\\git\\bin\\gzip.exe -dc c:\\me\\git\\logviewer\\resource\\test3.log.gz"),
                new FileLoader("resource\\test2.log"));
        this.parser = new SampleLogParser();
    }

    final List<LogLoader> loaders;
    final LogParser parser;

    public void onLoadButton() {
        CompletableFuture.supplyAsync(() ->
            loaders.stream()
                .map(loader -> {
                    app.getTaskController().start(loader.name());
                    return new SingleLogCollector(loader, parser).lineViews()
                            .thenApply(v -> {
                                app.getTaskController().end(loader.name());
                                return v;
                            });
                })
                .collect(Collectors.toList()).stream()
                .map(f -> f.join())
                .reduce(new ArrayList<>(), (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                })
        ).thenAccept(list -> Platform.runLater(() -> app.getTable().setItems(FXCollections.observableArrayList(list))));
    }
}

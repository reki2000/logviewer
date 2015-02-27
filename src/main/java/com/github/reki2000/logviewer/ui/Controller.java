package com.github.reki2000.logviewer.ui;

import com.github.reki2000.logviewer.collecter.SingleLogCollector;
import com.github.reki2000.logviewer.loader.FileLoader;
import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.loader.PipeLoader;
import com.github.reki2000.logviewer.parser.LogParser;
import com.github.reki2000.logviewer.parser.LtsvLogParser;
import com.github.reki2000.logviewer.parser.SampleLogParser;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Controller {
    final Main app;

    public Controller(Main app) {
        this.app = app;

        this.loaders = new ArrayList<LogLoader>();
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("logviewer.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (int i=0; i<100; i++) {
            String cmd = props.getProperty("cmd." + i);
            if (cmd != null) {
                loaders.add(new PipeLoader("cmd" + i, cmd));
            }
            String file = props.getProperty("file." + i);
            if (file != null) {
                loaders.add(new FileLoader(file));
            }
        }
        this.parser = new SampleLogParser();
    }

    final List<LogLoader> loaders;
    final LogParser parser;

    public void onLoadButton() {
        CompletableFuture.supplyAsync(() ->
            loaders.stream()
                .map(loader -> {
                    app.getTaskController().start(loader.name());
                    return new SingleLogCollector(loader, parser).collectAsync()
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

package com.github.reki2000.logviewer.ui;

import com.github.reki2000.logviewer.loader.Param;
import com.github.reki2000.logviewer.collecter.SingleLogCollector;
import com.github.reki2000.logviewer.loader.impl.FileLoaderBuilder;
import com.github.reki2000.logviewer.loader.LogLoaderBuilder;
import com.github.reki2000.logviewer.loader.impl.PipeLoaderBuilder;
import com.github.reki2000.logviewer.parser.LogParser;
import com.github.reki2000.logviewer.parser.SampleLogParser;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Controller {
    final Main app;

    public Controller(Main app) {
        this.app = app;

        this.builders = new ArrayList<LogLoaderBuilder>();
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("logviewer.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (int i=0; i<100; i++) {
            String cmd = props.getProperty("log." + i + ".cmd");
            if (cmd != null) {
                builders.add(new PipeLoaderBuilder("pipe" + i, cmd)); break;
            }

            String file = props.getProperty("log." + i + ".file");
            if (file != null) {
                builders.add(new FileLoaderBuilder("file" + i, file)); break;
            }
        }
        this.parser = new SampleLogParser();
    }

    final List<LogLoaderBuilder> builders;
    final LogParser parser;
    final List<String> servers = Arrays.asList("server1", "server2", "server3");

    public void onLoadButton(String yyyymmdd, String keyword) {
        CompletableFuture.supplyAsync(() ->
            builders.stream()
                .flatMap(builder -> builder.build(servers.stream(), new Param(yyyymmdd, keyword)))
                .map(loader -> {
                    app.getTaskController().start(loader.name());
                    return new SingleLogCollector(loader, parser)
                            .collectAsync()
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

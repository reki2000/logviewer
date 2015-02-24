package com.github.reki2000.logviewer.ui;

import com.github.reki2000.logviewer.collecter.LogCollector;
import com.github.reki2000.logviewer.collecter.ParallelCollector;
import com.github.reki2000.logviewer.loader.FileLoader;
import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.loader.PipeLoader;
import com.github.reki2000.logviewer.model.LineView;
import com.github.reki2000.logviewer.parser.SampleLogParser;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Controller {
    final Main app;

    public Controller(Main app) {
        this.app = app;
    }

    List<LogLoader> loaders = Arrays.asList(
            new PipeLoader("c:\\apps\\git\\bin\\cat.exe c:\\me\\git\\logviewer\\resource\\test.log"),
            new PipeLoader("c:\\apps\\git\\bin\\gzip.exe -dc c:\\me\\git\\logviewer\\resource\\test3.log.gz"),
            new FileLoader("resource\\test2.log"));
    LogCollector collector = new ParallelCollector(loaders, new SampleLogParser());

    Set<String> runningTasks = new HashSet<>();

    public void updateTask(Set<String> tasks) {
        app.getTaskBar().getChildren().clear();
        app.getTaskBar().getChildren().addAll(tasks.stream().map(t -> new Label(t)).collect(Collectors.toSet()));
    }

    public void onLoadButton() {
        runningTasks.add("NEW TASK");
        updateTask(runningTasks);
        CompletableFuture.supplyAsync(() -> collector.lineViews().collect(Collectors.toList()))
            .thenAccept(v -> onLoad(v));
    }

    public void onLoad(List<LineView> list) {
        app.getTable().setItems(FXCollections.observableArrayList(list));
        runningTasks.clear();
        updateTask(runningTasks);
    }
}

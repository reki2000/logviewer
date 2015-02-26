package com.github.reki2000.logviewer.ui;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TaskController {
    HBox app;

    public TaskController(HBox app) {
        this.app = app;
    }

    public void start(String name) {
        System.out.println("starts: " + name);
        runningTasks.add(name);
        updateUI(runningTasks);
    }

    public void end(String name) {
        System.out.println("ends: " + name);
        runningTasks.remove(name);
        updateUI(runningTasks);
    }

    Set<String> runningTasks = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

    void updateUI(Set<String> tasks) {
        Platform.runLater(() -> {
            app.getChildren().clear();
            app.getChildren().addAll(tasks.stream().map(t -> new Label(t)).collect(Collectors.toSet()));
        });
    }
}

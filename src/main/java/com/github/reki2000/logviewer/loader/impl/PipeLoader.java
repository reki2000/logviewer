package com.github.reki2000.logviewer.loader.impl;

import com.github.reki2000.logviewer.loader.LogLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PipeLoader implements LogLoader {
    private String[] cmdArgs;
    private String name;

    public PipeLoader(String name, String[] cmdArgs) {
        this.name = name;
        this.cmdArgs = cmdArgs;
    }

    public Stream<String> stream() throws IOException {
        Process p = Runtime.getRuntime().exec(cmdArgs);
        BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        CompletableFuture.runAsync(() -> System.out.println(stderr.lines().map(s -> name + ": " + s).collect(Collectors.joining("\n"))));
        return stdout.lines();
    }

    public String name() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<cmdArgs.length; i++) {
            sb.append(" " + cmdArgs[i]);
        }
        return this.name + sb.toString();
    }
}

package com.github.reki2000.logviewer.loader.impl;

import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.loader.LogLoaderBuilder;

public class FileLoaderBuilder implements LogLoaderBuilder {
    private String fileName;
    private String name;

    public FileLoaderBuilder(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    @Override public LogLoader buildLoader(String name, String fileName) {
        return new FileLoader(fileName);
    }

    @Override public String getTarget() {
        return this.fileName;
    }
}

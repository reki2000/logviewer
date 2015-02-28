package com.github.reki2000.logviewer.loader.impl;

import com.github.reki2000.logviewer.lib.CsvParser;
import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.loader.LogLoaderBuilder;

import java.util.List;

public class PipeLoaderBuilder implements LogLoaderBuilder {
    private String commandLine;

    public PipeLoaderBuilder(String name, String commandLine) {
        this.commandLine = commandLine;
    }

    @Override public LogLoader buildLoader(String name, String cmd) {
        List<String> params = new CsvParser().parse(cmd, ' ');
        String[] cmdVars = (params == null)
                ? new String[] { "NO CMD" }
                : params.toArray(new String[0]);
        return new PipeLoader(name, cmdVars);
    }

    @Override public String getTarget() {
        return this.commandLine;
    }
}

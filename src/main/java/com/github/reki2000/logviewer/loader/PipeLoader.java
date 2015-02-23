package com.github.reki2000.logviewer.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class PipeLoader implements LogLoader {
    private String cmd;

    public PipeLoader(String cmd) {
        this.cmd = cmd;
    }

    public Stream<String> stream() throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(cmd).getInputStream()));
        return r.lines();
    }
}

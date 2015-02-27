package com.github.reki2000.logviewer.loader;

import com.github.reki2000.logviewer.core.CsvParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

public class PipeLoader implements LogLoader {
    private String[] cmd;
    private String name;

    public PipeLoader(String name, String commandLine) {
        this.name = name;

        List<String> params = new CsvParser().parse(commandLine, ' ');
        if (params == null) {
            this.cmd = new String[] { "NO CMD" };
        } else {
            this.cmd = params.toArray(new String[0]);
        }
//        for(int i=0; i<cmd.length; i++) {
//            System.out.println("cmd[" + i + "]:" + cmd[i]);
//        }
    }

    public Stream<String> stream() throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(cmd).getInputStream()));
        return r.lines();
    }

    public String name() {
        return this.name;
    }
}

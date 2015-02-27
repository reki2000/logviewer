package com.github.reki2000.logviewer.parser;

import com.github.reki2000.logviewer.model.LineView;
import com.github.reki2000.logviewer.core.LtsvParser;

import java.util.Map;

public class LtsvLogParser implements LogParser {
    LtsvParser parser = new LtsvParser();

    public LineView parseLine(String line) {
        Map<String,String> cols = parser.parse(line);
        try {
            return new LineView(
                    cols.getOrDefault("host", "-"),
                    cols.getOrDefault("uri", "-"),
                    Integer.parseInt(cols.getOrDefault("port", "0")),
                    cols.getOrDefault("systemcode", "-"),
                    cols.getOrDefault("worker", "-"),
                    cols.getOrDefault("time", "-"),
                    "-".equals(cols.getOrDefault("status","-")) ? 0 : Integer.parseInt(cols.getOrDefault("status", "0")),
                    "-".equals(cols.getOrDefault("usec", "-")) ? 0 : Integer.parseInt(cols.getOrDefault("elapsed", "0")),
                    cols.getOrDefault("ref", "-"),
                    cols.getOrDefault("ua", "-"),
                    cols.getOrDefault("uniq", "-")
            );
        } catch (Exception e) {
            System.out.println("Invalid line: " + line + " e: "+ e.getMessage());
        }
        return null;
    }
}

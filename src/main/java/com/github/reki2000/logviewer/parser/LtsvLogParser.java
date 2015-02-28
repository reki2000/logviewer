package com.github.reki2000.logviewer.parser;

import com.github.reki2000.logviewer.model.LineView;
import com.github.reki2000.logviewer.lib.LtsvParser;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LtsvLogParser implements LogParser {
    LtsvParser parser = new LtsvParser();

    ZonedDateTime parseApacheTime(String s) {
        return ZonedDateTime.parse(s, DateTimeFormatter.ofPattern("'['dd/MMM/uuuu:HH:mm:ssZ']'"));
    }


    public LineView parseLine(String line) {
        Map<String,String> cols = parser.parse(line);
        try {
            return new LineView(
                    cols.getOrDefault("host", "-"),
                    cols.getOrDefault("uri", "-"),
                    Integer.parseInt(cols.getOrDefault("port", "0")),
                    cols.getOrDefault("systemcode", "-"),
                    cols.getOrDefault("worker", "-"),
                    parseApacheTime(cols.getOrDefault("time", "-")),
                    "-".equals(cols.getOrDefault("status","-")) ? 0 : Integer.parseInt(cols.getOrDefault("status", "0")),
                    "-".equals(cols.getOrDefault("usec", "-")) ? 0 : Integer.parseInt(cols.getOrDefault("elapsed", "0")),
                    cols.getOrDefault("ref", "-"),
                    cols.getOrDefault("ua", "-"),
                    cols.getOrDefault("uniq", "-")
            );
        } catch (Exception e) {
            System.out.println("Invalid line: " + line + " error: "+ e.getMessage());
        }
        return null;
    }
}

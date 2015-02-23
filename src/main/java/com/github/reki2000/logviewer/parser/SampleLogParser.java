package com.github.reki2000.logviewer.parser;

import com.github.reki2000.logviewer.model.LineView;

import java.util.List;

public class SampleLogParser implements LogParser {
    CsvParser parser = new CsvParser();

    public LineView parseLine(String line) {
        List<String> cols = parser.parse(line, ' ');
        try {
            if (cols.size() == 8) {
                return new LineView(
                        cols.get(0),
                        cols.get(5),
                        80,
                        cols.get(1),
                        cols.get(2),
                        cols.get(3) + cols.get(4),
                        Integer.valueOf(cols.get(6)),
                        Integer.valueOf(cols.get(7)),
                        "", ""
                );
            }
        } catch (Exception e) {
            System.out.println("Invalid line: " + line + " e: "+ e.getMessage());
        }
        return null;
    }
}

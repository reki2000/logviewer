package com.github.reki2000.logviewer.parser;

import com.github.reki2000.logviewer.lib.CsvParser;
import com.github.reki2000.logviewer.model.LineView;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class SampleLogParser implements LogParser {
    CsvParser parser = new CsvParser();

    static DateTimeFormatter apacheDateFormat = DateTimeFormatter.ofPattern("'['dd/MMM/uuuu:HH:mm:ssZ']'").withLocale(Locale.US);
    ZonedDateTime parseApacheTime(String s) {
        return ZonedDateTime.parse(s, apacheDateFormat);
    }

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
                        parseApacheTime(cols.get(3) + cols.get(4)),
                        "-".equals(cols.get(6)) ? 0 : Integer.parseInt(cols.get(6)),
                        "-".equals(cols.get(7)) ? 0 : Integer.parseInt(cols.get(7)),
                        "-", "-", "-"
                );
            }
        } catch (Exception e) {
            System.out.println("Invalid line: " + line + " error: "+ e.getMessage());
        }
        return null;
    }
}

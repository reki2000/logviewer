package com.github.reki2000.logviewer.parser;

import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    final private char QUOTE = '"';
    final private String ESCAPE_QUOTE = "\"\"";

    public List<String> parse(String line, char delimiter) {
        List<String> result = new ArrayList<>();
        boolean quoting = false;
        StringBuilder buffer = new StringBuilder();
        for (int i=0; i<line.length(); i++) {
            if (quoting) {
                if (line.substring(i).startsWith(ESCAPE_QUOTE)) {
                    buffer.append(QUOTE);
                    i += ESCAPE_QUOTE.length() - 1;
                    continue;
                }
                if (line.charAt(i) == QUOTE) {
                    quoting = false;
                    continue;
                }
            } else {
                if (line.charAt(i) == delimiter) {
                    result.add(buffer.toString());
                    buffer = new StringBuilder();
                    continue;
                }
                if (line.charAt(i) == QUOTE) {
                    quoting = true;
                    continue;
                }
            }
            buffer.append(line.charAt(i));
        }
        if (buffer.length() > 0) {
            result.add(buffer.toString());
        }
        return result;
    }
}

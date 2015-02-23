package com.github.reki2000.logviewer.parser;

import com.github.reki2000.logviewer.model.LineView;

public interface LogParser {
    public LineView parseLine(String line);
}

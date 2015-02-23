package com.github.reki2000.logviewer.collecter;

import com.github.reki2000.logviewer.model.LineView;

import java.util.stream.Stream;

public interface LogCollector {
    public Stream<LineView> lineViews();
}

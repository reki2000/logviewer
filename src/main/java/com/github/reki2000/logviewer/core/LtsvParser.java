package com.github.reki2000.logviewer.core;

import java.util.HashMap;
import java.util.Map;

public class LtsvParser {

    public Map<String,String> parse(String line) {
        Map<String,String> map = new HashMap<>();
        for (String s : line.split("\t")) {
           int pos = s.indexOf(':');
           String key = s.substring(0, pos);
           String value = s.substring(pos + 1);
           map.put(key, value);
       }
       return map;
    }
}

package com.github.reki2000.logviewer.parser;

import com.github.reki2000.logviewer.lib.CsvParser;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CsvParserTest {

    @Test
    public void should_split_by_delimiter() {
        CsvParser p = new CsvParser();
        Collection<String> r = p.parse("123 456", ' ');
        assertThat(r.stream().reduce("", (a, b) -> a + "," + b), is(",123,456"));
    }

    @Test
    public void should_handle_quote() {
        CsvParser p = new CsvParser();
        Collection<String> r = p.parse("123 \"456\"", ' ');
        assertThat(r.stream().reduce("", (a, b) -> a + "," + b), is(",123,456"));
    }

    @Test
    public void should_ignore_delimiter_in_quote() {
        CsvParser p = new CsvParser();
        Collection<String> r = p.parse("123 \"45 6\"", ' ');
        assertThat(r.stream().reduce("", (a, b) -> a + "," + b), is(",123,45 6"));
    }

    @Test
    public void should_ignore_quoted_quote_() {
        CsvParser p = new CsvParser();
        Collection<String> r = p.parse("123 \"45\"\"6\"", ' ');
        assertThat(r.stream().reduce("", (a, b) -> a + "," + b), is(",123,45\"6"));
    }
}

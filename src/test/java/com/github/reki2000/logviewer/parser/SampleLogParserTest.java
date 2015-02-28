package com.github.reki2000.logviewer.parser;

import org.junit.Test;

import java.time.ZonedDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SampleLogParserTest {

    @Test
    public void should_decode_apache_format_time() {
        ZonedDateTime time = new SampleLogParser().parseApacheTime("[12/Mar/2004:12:23:41-0800]");
        assertThat(time.getYear(), is(2004));
    }

//    @Test
//    public void should_encode() {
//        String encoded = DateTimeFormatter.ofPattern("'['dd/MMM/uuuu:HH:mm:ssZ']'").withLocale(Locale.US).format(ZonedDateTime.now());
//        System.out.println(encoded);
//    }
}

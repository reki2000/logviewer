package com.github.reki2000.logviewer.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class LineView {
    private final String  id;
    private final ZonedDateTime time;
    private final String host;
    private final String server;
    private final String  uri;
    private final int port;
    private final int status;
    private final int elapsed;
    private final String  referrer;
    private final String  ua;
    private final String  user;

    public LineView(String host, String uri, int port, String user, String server, ZonedDateTime time, int status, int elapsed, String referrer, String ua, String id) {
        this.id       = id;
        this.time     = time;
        this.host     = host;
        this.server   = server;
        this.uri      = uri;
        this.port     = port;
        this.status   = status;
        this.elapsed  = elapsed;
        this.referrer = referrer;
        this.ua       = ua;
        this.user     = user;
    }

    public String getHost()     { return host; }
    public String getUri()      { return uri; }
    public String getUser()     { return user; }
    public String getServer()   { return server; }
    public String getReferrer() { return referrer; }
    public String getUa()       { return ua; }
    public int    getStatus()   { return status; }
    public int    getElapsed()  { return elapsed; }
    public int    getPort()     { return port; }
    public String getTime()     { return time.toString(); }
    public String getId()       { return id; }
}

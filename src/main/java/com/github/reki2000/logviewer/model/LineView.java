package com.github.reki2000.logviewer.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class LineView {
    private final SimpleStringProperty  id;
    private final ZonedDateTime time;
    private final SimpleStringProperty  host;
    private final SimpleStringProperty  server;
    private final SimpleStringProperty  uri;
    private final SimpleIntegerProperty port;
    private final SimpleIntegerProperty status;
    private final SimpleIntegerProperty elapsed;
    private final SimpleStringProperty  referrer;
    private final SimpleStringProperty  ua;
    private final SimpleStringProperty  user;

    public LineView(String host, String uri, int port, String user, String server, ZonedDateTime time, int status, int elapsed, String referrer, String ua, String id) {
        this.id       = new SimpleStringProperty(id);
        this.time     = time;
        this.host     = new SimpleStringProperty(host);
        this.server   = new SimpleStringProperty(server);
        this.uri      = new SimpleStringProperty(uri);
        this.port     = new SimpleIntegerProperty(port);
        this.status   = new SimpleIntegerProperty(status);
        this.elapsed  = new SimpleIntegerProperty(elapsed);
        this.referrer = new SimpleStringProperty(referrer);
        this.ua       = new SimpleStringProperty(ua);
        this.user     = new SimpleStringProperty(user);
    }

    public String getHost()     { return host.get(); }
    public String getUri()      { return uri.get(); }
    public String getUser()     { return user.get(); }
    public String getServer()   { return server.get(); }
    public String getReferrer() { return referrer.get(); }
    public String getUa()       { return ua.get(); }
    public int    getStatus()   { return status.get(); }
    public int    getElapsed()  { return elapsed.get(); }
    public int    getPort()     { return port.get(); }
    public String getTime()     { return time.toString(); }
    public String getId()       { return id.get(); }
}

package com.github.reki2000.logviewer.loader;

import com.github.reki2000.logviewer.loader.LogLoader;
import com.github.reki2000.logviewer.loader.Param;

import java.util.stream.Stream;

public interface LogLoaderBuilder {

    default public Stream<LogLoader> build(Stream<String> servers, Param param) {
        return servers.map(server -> {
            String newCmd = getTarget()
                    .replaceAll("%server", server)
                    .replaceAll("%yyyy", param.getYYYY())
                    .replaceAll("%mm", param.getMM())
                    .replaceAll("%dd", param.getDD());
            return buildLoader(server, newCmd);
        });
    }

    String getTarget();

    LogLoader buildLoader(String server, String cmd);
}

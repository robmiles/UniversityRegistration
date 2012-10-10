package com.thoughtworks.universityregistration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class AppServer {

    private Server server;

    public AppServer(int port) throws Exception {
        server = new Server(port);
        server.setHandler(new WebAppContext("web/src/main/webapp", "/"));
        server.start();
    }

    public void join() throws InterruptedException {
        server.join();
    }

    public static void main(String[] args) throws Exception {
        AppServer appServer = new AppServer(63000);
        appServer.join();
    }
}

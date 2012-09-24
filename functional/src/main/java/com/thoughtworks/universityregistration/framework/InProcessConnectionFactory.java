package com.thoughtworks.universityregistration.framework;

import com.thoughtworks.inproctester.jetty.HttpAppTester;

import static java.lang.Runtime.getRuntime;

public class InProcessConnectionFactory {

    private static HttpAppTester connection;

    public static HttpAppTester getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        connection = new HttpAppTester("../web/src/main/webapp", "/");
        stopConnectionOnShutdown();
        connection.start();
    }

    private static void stopConnectionOnShutdown() {
        getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                connection.stop();
            }
        });
    }

}

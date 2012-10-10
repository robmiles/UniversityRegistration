package com.thoughtworks.universityregistration.infrastructure.hibernate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesLoader {

    private static final Properties properties = new Properties();
    static {

        try {
            FileInputStream applicationConfiguration = new FileInputStream(System.getProperty("appConfig"));
            properties.load(applicationConfiguration);
            applicationConfiguration.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getDatabaseUserName(){
        return properties.getProperty("database.username");
    }

    public static String getDatabasePassword(){
        return properties.getProperty("database.password", "");
    }

}

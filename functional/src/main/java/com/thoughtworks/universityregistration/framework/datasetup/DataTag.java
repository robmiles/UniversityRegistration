package com.thoughtworks.universityregistration.framework.datasetup;

public class DataTag {

    private String name;

    public DataTag(String name) {
        this.name = name;
    }

    public static DataTag taggedAs(String name) {
        return new DataTag(name);
    }

    public String getName() {
        return name;
    }
}

package com.thoughtworks.universityregistration.web.infrastructure;

public enum ModelKeys {
    ;

    private final String key;

    private ModelKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

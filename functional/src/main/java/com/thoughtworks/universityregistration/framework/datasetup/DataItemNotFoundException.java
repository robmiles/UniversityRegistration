package com.thoughtworks.universityregistration.framework.datasetup;

public class DataItemNotFoundException extends RuntimeException {
    public DataItemNotFoundException(String tagName) {
        super(tagName + " not found");
    }
}

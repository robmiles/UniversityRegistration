package com.thoughtworks.universityregistration.framework;

import com.thoughtworks.universityregistration.framework.datasetup.DataTag;
import com.thoughtworks.universityregistration.framework.datasetup.DataWriter;
import com.thoughtworks.universityregistration.framework.datasetup.builders.Builder;

import java.util.*;

public class Framework {

    private static Map<String, Object> dataItems = new HashMap<String, Object>();
    private static boolean persisted;

    public static void clearAllData() throws Exception {
        DataWriter.clearData();
        dataItems = new HashMap<String, Object>();
        persisted = false;
    }

    @SuppressWarnings("unchecked")
    public static <$DataItem> $DataItem getItem(String tagName) {
        Object item = dataItems.get(tagName);
        if (item == null) {
            throw new DataItemNotFoundException(tagName);
        }
        return ($DataItem) item;
    }

    @SuppressWarnings("unchecked")
    public static <$DataItem> List<$DataItem> getAll(Class klass) {
        List<$DataItem> dataItems = new ArrayList<$DataItem>();
        for (Object item : Framework.dataItems.values()) {
            if (klass == item.getClass()) {
                dataItems.add(($DataItem) item);
            }
        }
        return dataItems;
    }

    public static <$Builder extends Builder> void given($Builder argument) {
        Object dataItem = argument.build();
        dataItems.put(UUID.randomUUID().toString(), dataItem);
    }

    public static <$Builder extends Builder> void given($Builder argument, DataTag dataTag) {
        Object dataItem = argument.build();
        dataItems.put(dataTag.getName(), dataItem);
    }

    public static <$Builder extends Builder> void and($Builder argument) {
        Object dataItem = argument.build();
        dataItems.put(UUID.randomUUID().toString(), dataItem);
    }

    public static <$Builder extends Builder> void and($Builder argument, DataTag dataTag) {
        Object dataItem = argument.build();
        dataItems.put(dataTag.getName(), dataItem);
    }

}

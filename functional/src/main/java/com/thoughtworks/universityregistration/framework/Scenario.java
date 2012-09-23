package com.thoughtworks.universityregistration.framework;

import org.junit.Before;

import static com.thoughtworks.universityregistration.framework.Framework.clearAllData;

public abstract class Scenario {

    @Before
    public void beforeEachTest() throws Exception {
        clearAllData();
    }
}

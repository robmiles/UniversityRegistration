package com.thoughtworks.universityregistration.framework.pagemodel;

public class Assertion<$PageComponent extends PageComponent> {

    protected final $PageComponent component;

    protected Assertion($PageComponent component) {

        this.component = component;
    }
}

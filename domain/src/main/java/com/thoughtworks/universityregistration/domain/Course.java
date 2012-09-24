package com.thoughtworks.universityregistration.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    private String name;

    private Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
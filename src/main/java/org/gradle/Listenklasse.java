package org.gradle;

import org.apache.commons.collections.list.GrowthList;

public class Listenklasse {
    private final String name;

    public Listenklasse(String name) {
        this.name = name;
        new GrowthList();
    }

    public String getName() {
        return name;
    }
}

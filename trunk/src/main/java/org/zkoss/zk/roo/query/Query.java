package org.zkoss.zk.roo.query;

import org.zkoss.zk.ui.Component;

import java.util.List;

public class Query {

    private final List<Component> components;

    public Query(List<Component> comps) {
        this.components = comps;
    }

    public String text() {
        return "";
        // return components.get(0).getText();
    }

}

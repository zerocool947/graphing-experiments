package com.poorfellow.graphing.experimental.JUNG;

import org.apache.commons.collections15.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 11/15/2014.
 */
public class EdgeFactory implements Factory<String> {

    private int edgeNumbers;
    private static EdgeFactory edgeFactory;

    public static EdgeFactory getInstance() {
        if (edgeFactory == null) {
            edgeFactory = new EdgeFactory();
        }

        return edgeFactory;
    }

    public EdgeFactory() {
        edgeNumbers = 0;
    }

    @Override
    public String create() {
        edgeNumbers++;
        return "Edge" + edgeNumbers;
    }
}

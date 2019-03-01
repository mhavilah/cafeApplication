package com.thesolutionlab.model;

import java.util.Arrays;

public enum BeverageType {
    Latte("Latte"),
    Mocha("Mocha"),
    Cappuccino("Capp");

    private String label;

    BeverageType( String label )
    {

        this.label = label;
    }

    public String getLabel()
    {
        return this.label;
    }

    public static String allValues()
    {
        return Arrays.asList(BeverageType.values())
            .stream()
            .reduce(new StringBuffer(""),
                    (sb, bev) -> (sb.append(bev.name()+":")), StringBuffer::append)
            .toString();
    }

}

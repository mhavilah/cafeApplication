package com.thesolutionlab.model;

public class OrderItem {
    public Enum<BeverageType> beverageType;
    public int numberOfCups;

    public OrderItem( Enum<BeverageType> beverageType, int numberOfCups )
    {
        this.beverageType = beverageType;
        this.numberOfCups = numberOfCups;
    }
}

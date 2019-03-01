package com.thesolutionlab.model;

import java.util.Objects;

public class OrderItem {
    public Enum<BeverageType> beverageType;
    public int numberOfCups;

    public OrderItem( Enum<BeverageType> beverageType, int numberOfCups )
    {
        this.beverageType = beverageType;
        this.numberOfCups = numberOfCups;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderItem orderItem = (OrderItem) o;
        return numberOfCups == orderItem.numberOfCups &&
            beverageType.equals(orderItem.beverageType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(beverageType, numberOfCups);
    }
}

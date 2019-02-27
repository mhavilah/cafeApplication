package com.thesolutionlab.model;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderItem> items;

    public Order()
    {
        items = new ArrayList<>();
    }

    public Order addBeverage(String beverage, int cups )
    {
        return addBeverage( BeverageType.valueOf(beverage), cups );
    }

    public Order addBeverage(Enum<BeverageType> beverageType, int cups )
    {
        items.add( new OrderItem( beverageType, cups ));
        return this;
    }

    public boolean equals(Object object) {

        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        if (!super.equals(object))
            return false;
        Order order = (Order) object;
        return java.util.Objects.equals(getItems(), order.getItems());
    }

    public void setItems(List<OrderItem> items) {

        this.items = items;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public static Order order( String beverage, int cups )
    {
        return new Order().addBeverage( beverage, cups );
    }
}
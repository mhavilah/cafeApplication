package com.thesolutionlab;

import com.thesolutionlab.exception.UnavailableProductException;
import com.thesolutionlab.model.BeverageType;
import com.thesolutionlab.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// https://stackoverflow.com/questions/43280250/how-do-i-use-hamcrest-with-junit-5-when-junit-5-doesnt-have-an-assertthat-fun

public class WaiterTest {

    private static final int INITIAL_INVENTORY_LEVELS = 5;
    private Waiter waiter;

    @BeforeEach
    public void setupCafe()
    {

        waiter = new Waiter();

        CafeService cafeService = new CafeServiceImpl(INITIAL_INVENTORY_LEVELS);

        waiter.setCafeService(cafeService);
    }

    @Test
    public void canOrderCoffee()
    {

        Order order = new Order();
        order.addBeverage(BeverageType.Cappuccino, 1);

        BigDecimal price = waiter.takeOrder(order);

        assertThat(price.compareTo(CafeServiceImpl.UNIT_PRICE), is(0));
    }

    @Test
    public void tooMuchCoffeeCausesHeadaches()
    {

        Order order = new Order();
        order.addBeverage(BeverageType.Cappuccino, INITIAL_INVENTORY_LEVELS + 1);

        assertThrows(UnavailableProductException.class,
            () -> {
                BigDecimal price = waiter.takeOrder(order);
            });
    }

    /**
     * Waiters do not like to take orders with negative amounts of cups
     */
    @Test
    public void negativeCupsAreNotGood()
    {
        Order order = new Order();
        order.addBeverage(BeverageType.Cappuccino, -1);

        assertThrows(IllegalArgumentException.class,
            () -> {
                waiter.takeOrder(order);
            });
    }

}
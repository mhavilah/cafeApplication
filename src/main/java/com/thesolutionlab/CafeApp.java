package com.thesolutionlab;

import com.thesolutionlab.model.BeverageType;
import com.thesolutionlab.model.Order;

import java.util.Optional;

/**
 * A Simple Main class for invoking the CafeService via the Waiter.
 * <p>
 * Usage:   java -jar CafeApp.jar  3 Latte
 * </p>
 */
public class CafeApp {

    public static final int INITIAL_INVENTORY_LEVELS = 10;

    public static void main(String[] args)
    {
        Order order = getOrder(args);

        Optional.ofNullable(order)
                .ifPresent((anOrder)-> {
                        System.out.printf("%d cups of %s costs: %f\n",
                            anOrder.getItems().get(0).numberOfCups,
                            anOrder.getItems().get(0).beverageType,
                            new Waiter(new CafeServiceImpl(INITIAL_INVENTORY_LEVELS)).takeOrder(anOrder)
                        );
                });
    }

    private static Order getOrder(String[] args)
    {
        Order order = null;

        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("Invalid number of args");
            }

            order = Order.order( args[1], Integer.parseInt(args[0]) );

        } catch (IllegalArgumentException iae) {
            System.out.printf("Unknown beverage or invalid arguments:%s %s\n",
                 (args.length>=1)? "["+args[0]+"]": "[]",
                 (args.length>=2)? "["+args[1]+"]": "[]"
                );
            usage();
        }

        return order;
    }

    private static void usage()
    {

        System.out.println("Usage:");
        System.out.println("CafeApp [numberOfCups] [Beverage type]");
        System.out.println("CafeApp 1 Latte");
        System.out.println("CafeApp 3 Cappuccino");
        System.out.println("Possible beverages:  " + BeverageType.allValues());
    }
}

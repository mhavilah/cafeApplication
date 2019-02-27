package com.thesolutionlab;

import com.thesolutionlab.model.BeverageType;

import static com.thesolutionlab.model.Order.order;

public class CafeApp {

    public static void main(String[] args)
    {

        if (args.length == 2) {
            int cups = Integer.parseInt(args[0]);
            String beverage = args[1];

            System.out.printf("%d cups of %s costs: %f",
                cups, beverage,
                new Waiter(new CafeServiceImpl(10)).takeOrder(order(beverage, cups))
            );
        } else {
            usage();
        }
    }

    private static void usage()
    {

        System.out.println("CafeApp [numberOfCups] [Beverage type]");
        System.out.println("CafeApp 1 Latte");
        System.out.println("CafeApp 3 Capp");
        System.out.println("Possible beverages:  " + BeverageType.allValues());
    }
}

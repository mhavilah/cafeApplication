package com.thesolutionlab;

import com.thesolutionlab.model.Order;
import com.thesolutionlab.model.OrderItem;

import java.math.BigDecimal;

/**
 * A Waiter who takes orders and uses the CafeService.
 */
public class Waiter {

    private CafeService cafeService;

    public Waiter() {}

    public Waiter(CafeService service) { this.cafeService = service;}

    public BigDecimal takeOrder( Order order )
    {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for( OrderItem orderItem : order.getItems()) {
            BigDecimal itemPrice = cafeService.makeBeverage(orderItem.numberOfCups, orderItem.beverageType);

            totalPrice = totalPrice.add(itemPrice);
        }

        return totalPrice;
    }

    public void setCafeService(CafeService cafeService)
    {
        this.cafeService = cafeService;
    }
}

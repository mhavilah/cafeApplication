package com.thesolutionlab;

import com.thesolutionlab.model.Order;
import com.thesolutionlab.model.OrderItem;

import java.math.BigDecimal;

/**
 * A Waiter who takes orders and uses the CafeService.
 *
 * This client of CafeService will be tested with both a "production" <code>CafeServiceImpl</code>
 * and a Mock CafeServiceImpl.
 */
public class Waiter {

    private CafeService cafeService;

    public Waiter() {}

    public Waiter(CafeService service) { this.cafeService = service;}

    /**
     * Take an <code>Order</code> and make some Coffee.
     * <p>
     *     Uses the collaborating <code>CafeService</code> to process the order and obtain prices for each
     *  line item/<code>OrderItem</code> in the Order.
     * </p>
     * @param order         Order containing some OrderItems
     * @return              BigDecimal      aggregate price.
     * @throws              IllegalArgumentException    if an order item is malformed
     */
    public BigDecimal takeOrder( Order order )
    {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for( OrderItem orderItem : order.getItems()) {

            if (orderItem.numberOfCups < 0)
            {
                throw new IllegalArgumentException("Negative cups are not healthy");
            }

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

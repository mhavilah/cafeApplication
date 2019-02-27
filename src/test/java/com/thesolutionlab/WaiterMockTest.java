package com.thesolutionlab;

import com.thesolutionlab.exception.UnavailableProductException;
import com.thesolutionlab.model.BeverageType;
import com.thesolutionlab.model.Order;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// https://stackoverflow.com/questions/43280250/how-do-i-use-hamcrest-with-junit-5-when-junit-5-doesnt-have-an-assertthat-fun

public class WaiterMockTest {

    private static final int INITIAL_INVENTORY_LEVELS = 5;
    private Waiter waiter;

    @BeforeEach
    public void setupCafe()
    {

        waiter = new Waiter();

    }

    @Test
    public void canOrderMockCoffee()
    {
        CafeService cafeService = Mockito.mock(CafeService.class);
        waiter.setCafeService(cafeService);

        Mockito.when( cafeService.makeBeverage( 1, BeverageType.Cappuccino ))
                .thenReturn( BigDecimal.ONE );

        Order order = new Order();
        order.addBeverage(BeverageType.Cappuccino, 1);

        BigDecimal price = waiter.takeOrder(order);

        assertThat( price.compareTo(BigDecimal.ONE ), is(0));

    }

    @Test
    public void canMockOutOfInventory()
    {
        CafeService cafeService = Mockito.mock(CafeService.class);
        waiter.setCafeService(cafeService);

        Mockito.when( cafeService.makeBeverage( Mockito.anyInt(), Mockito.any( BeverageType.class) ))
            .thenThrow( new UnavailableProductException("mocked inventory exception"));

        Order order = new Order();
        order.addBeverage(BeverageType.Cappuccino, 1);

        assertThrows( UnavailableProductException.class,
            () -> { BigDecimal price = waiter.takeOrder(order); });
    }

    /**
     * This test illustrates using the Answer API to generate a mock response that
     * is dependent upon the request parameters.
     *
     * Note:
     * - the parameters to the mocked method are flagged with wildcard matchers (anyInt and any Beverage).
     * - the lambda is actually creating an Answer&lt;BigDecimal&gt;
     */
    @Test
    public void canMockViaAnAnswer()
    {
        CafeService cafeService = Mockito.mock(CafeService.class);
        waiter.setCafeService(cafeService);

        double ANOTHER_UNIT_PRICE = 5.0;

        Mockito.when( cafeService.makeBeverage(Mockito.anyInt(), Mockito.any(BeverageType.class)))
            .then( (invocation) -> {
                int cups = invocation.getArgument(0);
                BeverageType type = invocation.getArgument(1);

                return new BigDecimal( ANOTHER_UNIT_PRICE * cups );
            } );

        Order order = new Order();
        order.addBeverage(BeverageType.Cappuccino, 2);

        BigDecimal price = waiter.takeOrder(order);
        assertThat( price.compareTo( BigDecimal.TEN), is(0));
    }

    @Test
    public void answersCanAlsoThrowExceptions()
    {
        CafeService cafeService = Mockito.mock(CafeService.class);
        waiter.setCafeService(cafeService);

        double ANOTHER_UNIT_PRICE = 5.0;

        Mockito.when( cafeService.makeBeverage(Mockito.anyInt(), Mockito.any(BeverageType.class)))
            .then( (invocation) -> {
                int cups = invocation.getArgument(0);
                BeverageType type = invocation.getArgument(1);

                if (cups>10) { throw new UnavailableProductException("Too much coffee!!"); }

                return new BigDecimal( ANOTHER_UNIT_PRICE * cups );
            } );

        Order order = new Order();
        order.addBeverage(BeverageType.Cappuccino, 100);

        assertThrows( UnavailableProductException.class,
            () -> { BigDecimal price = waiter.takeOrder(order); });
    }
}

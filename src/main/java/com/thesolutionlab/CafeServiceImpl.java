package com.thesolutionlab;

import com.thesolutionlab.exception.UnavailableProductException;
import com.thesolutionlab.model.BeverageType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.thesolutionlab.model.BeverageType.*;

/**
 * An implementation of a Cafe with inventory level tracking.
 */
public class CafeServiceImpl implements CafeService {

    public static final BigDecimal UNIT_PRICE = new BigDecimal("4.00");

    private Map<Enum<BeverageType>, Integer> inventoryMap;

    public CafeServiceImpl(int initialInventoryLevels)
    {
        inventoryMap = new HashMap<>();
        inventoryMap.put(Latte, initialInventoryLevels);
        inventoryMap.put(Cappuccino, initialInventoryLevels);
        inventoryMap.put(Mocha, initialInventoryLevels);
    }

    /**
     * Make some coffee.
     *
     * @param numberOfCups      int
     * @param beverageType      BeverageType
     * @return                  BigDecimal      Price of the order
     * throws                   UnavailableProductException     if the order would exceed inventory levels
     */
    public BigDecimal makeBeverage(int numberOfCups, Enum<BeverageType> beverageType)
    {

        int currentInventory = inventoryMap.get(beverageType);
        int newInventory = currentInventory - numberOfCups;

        if (newInventory < 0) {
            throw new UnavailableProductException("No more supplies of" + beverageType.name());
        }

        inventoryMap.put(beverageType, newInventory );

        return UNIT_PRICE.multiply( BigDecimal.valueOf(numberOfCups ));
    }

}
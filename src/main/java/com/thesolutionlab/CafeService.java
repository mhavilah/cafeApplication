package com.thesolutionlab;


import com.thesolutionlab.model.BeverageType;

import java.math.BigDecimal;


public interface CafeService {
   BigDecimal makeBeverage(int numberOfCups, Enum<BeverageType> beverageType);
}
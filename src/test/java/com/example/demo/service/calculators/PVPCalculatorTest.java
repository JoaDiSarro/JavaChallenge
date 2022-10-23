package com.example.demo.service.calculators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PVPCalculatorTest {

    private final PVPCalculator sut = new PVPCalculator();

    private final Double price = 10.0;
    private final Double percentage = 20.0;
    private final Double expectedResult = 12.0;

    @Test
    void givenPriceAndPercentage_whenCalculatePVP_thenReturnRightResult() {
        assertEquals(expectedResult, sut.calculatePVP(price, percentage));
    }

}
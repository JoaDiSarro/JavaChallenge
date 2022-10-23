package com.example.demo.service.calculators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PVPCalculatorTest {

    private final PVPCalculator sut = new PVPCalculator();

    @Test
    void givenPriceAndPercentage_whenCalculatePVP_thenReturnRightResult() {
        Double price = 10.0;
        Double percentage = 20.0;
        Double expectedResult = 12.0;
        assertEquals(expectedResult, sut.calculatePVP(price, percentage));
    }

}
package com.example.demo.service.calculators;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class PVPCalculator {

    /**
     * Process that generates the pvp value for a specific price and percentage.
     * The price increases its value from the percentage
     *
     * @param price      that want to be increased
     * @param percentage percentage amount you want to increase
     * @return result
     */
    public Double calculatePVP(Double price, Double percentage) {
        return new BigDecimal(price * (1.0 + percentage / 100)).setScale(2, RoundingMode.FLOOR).doubleValue();
    }

}
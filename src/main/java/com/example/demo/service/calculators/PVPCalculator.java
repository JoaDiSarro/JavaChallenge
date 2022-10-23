package com.example.demo.service.calculators;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class PVPCalculator {

    public Double calculatePVP(Double price, Double percentage) {
        return new BigDecimal(price * (1.0 + percentage / 100)).setScale(2, RoundingMode.FLOOR).doubleValue();
    }

}
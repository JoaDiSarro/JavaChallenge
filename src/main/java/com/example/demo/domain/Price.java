package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Price {

    private int productId;
    private int brandId;
    private Date startDate;
    private Date endDate;
    private Double feePercentage;
    private Double pvp;

}
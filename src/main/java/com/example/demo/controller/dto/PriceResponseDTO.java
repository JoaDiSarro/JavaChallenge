package com.example.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

import static com.example.demo.controller.constants.DateConstants.DATE_PATTERN;
import static com.example.demo.controller.constants.DateConstants.DATE_TIMEZONE;

@Data
@Jacksonized
@Builder
public class PriceResponseDTO {

    private int productId;
    private int brandId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = DATE_TIMEZONE)
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = DATE_TIMEZONE)
    private Date endDate;
    private Double feePercentage;
    private Double pvp;

}
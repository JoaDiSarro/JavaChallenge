package com.example.demo.error.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessages {

    public static final String PRICE_NOT_FOUND_MESSAGE = "No price found for product %d of brand %d on the given date";

}
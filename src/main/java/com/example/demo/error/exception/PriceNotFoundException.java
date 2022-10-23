package com.example.demo.error.exception;

import lombok.Getter;

import static com.example.demo.error.constants.ErrorMessages.PRICE_NOT_FOUND_MESSAGE;

@Getter
public class PriceNotFoundException extends MainException {

    public PriceNotFoundException(final int productId, final int brandId) {
        super(String.format(PRICE_NOT_FOUND_MESSAGE, productId, brandId));
    }

    public PriceNotFoundException(final int productId, final int brandId, Throwable cause) {
        super(String.format(PRICE_NOT_FOUND_MESSAGE, productId, brandId), cause);
    }

}
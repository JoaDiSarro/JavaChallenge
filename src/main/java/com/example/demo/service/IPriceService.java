package com.example.demo.service;

import com.example.demo.domain.Price;

import java.util.Date;

public interface IPriceService {

    Price getBy(Date date, int productId, int brandId);

}
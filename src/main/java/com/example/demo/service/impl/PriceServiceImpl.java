package com.example.demo.service.impl;

import com.example.demo.domain.Price;
import com.example.demo.error.exception.PriceNotFoundException;
import com.example.demo.repository.FeeRepository;
import com.example.demo.repository.PriceRepository;
import com.example.demo.repository.dao.FeeDAO;
import com.example.demo.repository.dao.PriceDAO;
import com.example.demo.service.IPriceService;
import com.example.demo.service.calculators.PVPCalculator;
import com.example.demo.service.mapper.IPriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements IPriceService {

    private final PriceRepository repository;
    private final FeeRepository feeRepository;
    private final IPriceMapper mapper;
    private final PVPCalculator pvpCalculator;

    @Override
    public Price getBy(Date date, int productId, int brandId) {
        List<PriceDAO> prices = repository.getBy(date, productId, brandId);
        if (!prices.isEmpty()) {
            return getPriceWithGreaterPriority(prices);
        }
        throw new PriceNotFoundException(productId, brandId);
    }

    private Price getPriceWithGreaterPriority(List<PriceDAO> prices) {
        PriceDAO priceWithGreaterPriority = Collections.max(prices, Comparator.comparing(PriceDAO::getPriority));
        FeeDAO feeDAO = feeRepository.getReferenceById(priceWithGreaterPriority.getFeeId());
        return generatePrice(priceWithGreaterPriority, feeDAO);
    }

    private Price generatePrice(PriceDAO priceWithGreaterPriority, FeeDAO feeDAO) {
        Price price = mapper.daoToDomain(priceWithGreaterPriority);
        price.setFeePercentage(feeDAO.getPercentage());
        price.setPvp(pvpCalculator.calculatePVP(priceWithGreaterPriority.getPrice(), feeDAO.getPercentage()));
        return price;
    }

}
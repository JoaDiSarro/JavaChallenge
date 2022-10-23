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
import com.example.demo.util.InmutableList;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static com.example.demo.error.constants.ErrorMessages.PRICE_NOT_FOUND_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PriceServiceImplTest {

    private final PriceRepository repository = mock(PriceRepository.class);
    private final FeeRepository feeRepository = mock(FeeRepository.class);
    private final IPriceMapper mapper = mock(IPriceMapper.class);
    private final PVPCalculator pvpCalculator = mock(PVPCalculator.class);
    private final IPriceService sut = new PriceServiceImpl(repository, feeRepository, mapper, pvpCalculator);

    private final Double expectedPVP = 22.0;
    private final int id = 1;
    private final Date date = new Date();
    private final FeeDAO feeDAO = new FeeDAO(id, 10.0);
    private final PriceDAO priceDAO1 = new PriceDAO(id, id, id, date, date, feeDAO.getId(), 10.0, (byte) 1);
    private final PriceDAO priceDAO2 = new PriceDAO(id, id, id, date, date, feeDAO.getId(), 20.0, (byte) 2);
    private final List<PriceDAO> priceDAOList = InmutableList.of(priceDAO2);
    private final List<PriceDAO> priceDAOListWithMultipleValues = InmutableList.of(priceDAO1, priceDAO2);
    private final Price expectedPrice =
            Price.builder()
                    .productId(id)
                    .brandId(id)
                    .startDate(date)
                    .endDate(date)
                    .feePercentage(feeDAO.getPercentage())
                    .pvp(expectedPVP)
                    .build();
    private final Price expectedDomainPrice = Price.builder().productId(id).brandId(id).startDate(date).endDate(date).build();

    @Test
    void givenDateProductAndBrand_whenGetBy_thenCallValidatorRepositoryMapperAndReturnsPrice() {
        when(repository.getBy(any(Date.class), any(Integer.class), any(Integer.class))).thenReturn(priceDAOList);
        when(feeRepository.getReferenceById(id)).thenReturn(feeDAO);
        when(pvpCalculator.calculatePVP(priceDAO2.getPrice(), feeDAO.getPercentage())).thenReturn(expectedPVP);
        when(mapper.daoToDomain(any())).thenReturn(expectedDomainPrice);

        Price result = sut.getBy(date, id, id);

        assertEquals(expectedPrice, result);
        verify(repository).getBy(eq(date), eq(id), eq(id));
        verify(mapper).daoToDomain(priceDAO2);
    }

    @Test
    void givenDateProductAndBrandWithMultiplePricesResponse_whenGetBy_thenReturnsPriceWithGreaterPriority() {
        when(repository.getBy(any(Date.class), any(Integer.class), any(Integer.class))).thenReturn(priceDAOListWithMultipleValues);
        when(feeRepository.getReferenceById(id)).thenReturn(feeDAO);
        when(pvpCalculator.calculatePVP(priceDAO2.getPrice(), feeDAO.getPercentage())).thenReturn(expectedPVP);
        when(mapper.daoToDomain(any())).thenReturn(expectedDomainPrice);

        Price result = sut.getBy(date, id, id);

        assertEquals(expectedPrice, result);
        verify(repository).getBy(eq(date), eq(id), eq(id));
        verify(mapper).daoToDomain(priceDAO2);
    }

    @Test
    void givenDateProductAndBrandWithEmptyResponse_whenGetBy_thenThrowsException() {
        when(repository.getBy(any(Date.class), any(Integer.class), any(Integer.class))).thenReturn(InmutableList.of());

        try {
            sut.getBy(date, id, id);
            fail("Expected exception");
        } catch (PriceNotFoundException exception) {
            assertThat(exception).hasMessageContaining(PRICE_NOT_FOUND_MESSAGE, id, id);
            verify(repository).getBy(eq(date), eq(id), eq(id));
        }

    }

}
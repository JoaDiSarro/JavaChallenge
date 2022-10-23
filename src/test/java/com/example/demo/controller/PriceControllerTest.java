package com.example.demo.controller;

import com.example.demo.controller.dto.PriceResponseDTO;
import com.example.demo.controller.mapper.IPriceDTOMapper;
import com.example.demo.domain.Price;
import com.example.demo.service.IPriceService;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PriceControllerTest {

    private final IPriceService service = mock(IPriceService.class);
    private final IPriceDTOMapper mapper = mock(IPriceDTOMapper.class);
    private final PriceController sut = new PriceController(service, mapper);

    private final PriceResponseDTO response = mock(PriceResponseDTO.class);
    private final Price domain = mock(Price.class);

    @Test
    void givenDateProductIdAndBrandIdWhenGetByThenReturnsResponseDto() throws Exception {
        when(service.getBy(any(Date.class), any(int.class), any(int.class))).thenReturn(domain);
        when(mapper.domainToDTO(domain)).thenReturn(response);

        PriceResponseDTO result = sut.getBy(new Date(), 1, 1);

        assertThat(result).isEqualTo(result);
    }

}
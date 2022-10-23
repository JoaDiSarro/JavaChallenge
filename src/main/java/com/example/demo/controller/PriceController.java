package com.example.demo.controller;

import com.example.demo.controller.dto.PriceResponseDTO;
import com.example.demo.controller.mapper.IPriceDTOMapper;
import com.example.demo.service.IPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.example.demo.controller.constants.DateConstants.DATE_PATTERN;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {

    private final IPriceService service;
    private final IPriceDTOMapper mapper;

    @GetMapping()
    public PriceResponseDTO getBy(@RequestParam @DateTimeFormat(pattern = DATE_PATTERN) final Date date,
                                  @RequestParam final int productId,
                                  @RequestParam final int brandId) {
        return mapper.domainToDTO(service.getBy(date, productId, brandId));
    }
}
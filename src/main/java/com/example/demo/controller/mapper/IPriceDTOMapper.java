package com.example.demo.controller.mapper;

import com.example.demo.controller.dto.PriceResponseDTO;
import com.example.demo.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IPriceDTOMapper {

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "feePercentage", source = "feePercentage")
    @Mapping(target = "pvp", source = "pvp")
    PriceResponseDTO domainToDTO(Price price);

}
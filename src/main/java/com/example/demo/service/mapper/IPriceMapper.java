package com.example.demo.service.mapper;

import com.example.demo.domain.Price;
import com.example.demo.repository.dao.PriceDAO;
import org.mapstruct.Mapper;

@Mapper
public interface IPriceMapper {

    Price daoToDomain(PriceDAO price);

}
package com.example.demo.repository;

import com.example.demo.repository.dao.PriceDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface PriceRepository extends JpaRepository<PriceDAO, Integer> {

    @Query(value = "SELECT " +
            "P.ID, PRODUCT_ID, BRAND_ID, START_DATE, END_DATE, F.ID AS FEE_ID, F.PERCENTAGE AS FEE_PERCENTAGE, " +
            "P.PRIORITY, P.PRICE " +
            "FROM  " +
            "PRICES P, FEES F " +
            "WHERE " +
            "((?1 BETWEEN P.START_DATE AND P.END_DATE) AND P.PRODUCT_ID = ?2 AND P.BRAND_ID = ?3) " +
            "AND FEE_ID = F.ID", nativeQuery = true)
    List<PriceDAO> getBy(Date date, int productId, int brandId);

}
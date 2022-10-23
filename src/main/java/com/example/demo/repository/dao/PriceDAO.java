package com.example.demo.repository.dao;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRICES")
@Data
public class PriceDAO {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private int productId;
    @NotNull
    private int brandId;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @NotNull
    private int feeId;
    @NotNull
    private Double price;
    @NotNull
    private byte priority;

    public PriceDAO() {
    }

    public PriceDAO(int id, int productId, int brandId, Date startDate, Date endDate, int feeId, Double price, byte priority) {
        this.id = id;
        this.productId = productId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.feeId = feeId;
        this.price = price;
        this.priority = priority;
    }

}
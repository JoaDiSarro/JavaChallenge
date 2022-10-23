package com.example.demo.repository.dao;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FEES")
@Data
public class FeeDAO {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private Double percentage;

    public FeeDAO() {
    }

    public FeeDAO(int id, Double percentage) {
        this.id = id;
        this.percentage = percentage;
    }

}
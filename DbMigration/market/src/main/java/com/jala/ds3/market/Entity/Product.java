package com.jala.ds3.market.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "Products")
@Table(name = "Products")
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 130)
    private String name;

    @Column
    @Positive
    private float price;
}

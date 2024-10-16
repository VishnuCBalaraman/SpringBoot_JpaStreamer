package com.ust.sales_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Property")
public class Property{

    @Id
    private String id;
    private String propertyId;
    private String propertyType;
    private String buyerId;
    private String sellerId;
    private BigDecimal salePrice;
    private LocalDate saleDate;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private BigDecimal commissionRate;
    private BigDecimal commissionEarned;
    private boolean active;
    private List<String> agents;
    private List<String> buyers;

}

package com.ust.sales_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyDto {

    @NotBlank(message = "Property ID is mandatory")
    private String propertyId;

    @NotBlank(message = "Property type is mandatory")
    private String propertyType;

    @NotBlank(message = "Buyer ID is mandatory")
    private String buyerId;

    @NotBlank(message = "Seller ID is mandatory")
    private String sellerId;

    @NotNull(message = "Sale price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Sale price must be greater than 0")
    private BigDecimal salePrice;

    @NotNull(message = "Sale date is mandatory")
    private LocalDate saleDate;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "State is mandatory")
    private String state;

    @NotBlank(message = "Zip code is mandatory")
    private String zipCode;

    @NotNull(message = "Commission rate is mandatory")
    @DecimalMin(value = "0.0", message = "Commission rate must be at least 0")
    private BigDecimal commissionRate;

    private BigDecimal commissionEarned;

    private boolean active;

    private List<String> agents;

    private List<String> buyers;
}

package com.ust.sales_service.repository;

import com.ust.sales_service.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property,String> {
    Property findByPropertyId(String propertyId);
}

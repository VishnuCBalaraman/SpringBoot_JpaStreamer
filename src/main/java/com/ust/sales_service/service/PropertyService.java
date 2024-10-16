package com.ust.sales_service.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.sales_service.model.Property;
import com.ust.sales_service.repository.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepo;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public PropertyService(JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }


    public Property createProperty(Property property){
        propertyRepo.saveAndFlush(property);
        BigDecimal commEarned = calculateCommission(property.getPropertyId());
        property.setCommissionEarned(commEarned);
        return propertyRepo.saveAndFlush(property);
    }

    public List<Property> createProperties(List<Property> properties){
        propertyRepo.saveAllAndFlush(properties);
        for(Property property : properties){
            BigDecimal commEarned = calculateCommission(property.getPropertyId());
            property.setCommissionEarned(commEarned);
        }
        return propertyRepo.saveAllAndFlush(properties);
    }

    public Property getPropertyById(String propertyId){
        if(propertyId==null){
            return null;
        }
        //return propertyRepo.findByPropertyId(propertyId);
        return jpaStreamer.stream(Property.class).filter(p->p.getPropertyId().equals(propertyId)).findFirst().orElse(null);
    }

    public List<Property> getAllProperties(){
        return propertyRepo.findAll();
    }

    public Property updateProperty(Property updatedProperty){
        BigDecimal commEarned = calculateCommission(updatedProperty.getPropertyId());
        updatedProperty.setCommissionEarned(commEarned);
        return propertyRepo.saveAndFlush(updatedProperty);
    }

    public void deleteProperty(String propertyId){
        Property property = getPropertyById(propertyId);
        propertyRepo.delete(property);
    }

    // Property Management
    public List<Property> getActiveProperties(){
        return jpaStreamer.stream(Property.class).filter(Property::isActive).collect(Collectors.toList());
    }

    public List<Property> getPropertiesByType(String propertyType){
        return jpaStreamer.stream(Property.class).filter(p->p.getPropertyType().equals(propertyType)).collect(Collectors.toList());
    }
/*
    List<Property> searchProperties(String location, BigDecimal minPrice, BigDecimal maxPrice, List<String> features);

    // Showings Management
    void scheduleShowing(String propertyId, String buyerId, LocalDate date);
*/
    // Buyer-Seller Matching
    public List<Property> matchPropertiesToBuyer(String buyerId){
        return jpaStreamer.stream(Property.class).filter(p->p.getBuyerId().equals(buyerId)).collect(Collectors.toList());
    }


    // Commission Management
    public BigDecimal calculateCommission(String propertyId){
        Property property = getPropertyById(propertyId);
        if (property == null) {
            throw new IllegalArgumentException("Property with ID " + propertyId + " not found.");
        }
        BigDecimal commRate = property.getCommissionRate();
        BigDecimal salePrice = property.getSalePrice();
        BigDecimal commEarned = commRate.multiply(salePrice);
        return commEarned;
    }

}

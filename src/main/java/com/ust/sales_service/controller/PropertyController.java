package com.ust.sales_service.controller;

import com.ust.sales_service.model.Property;
import com.ust.sales_service.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public Property createProperty(@RequestBody @Valid Property property){
        return propertyService.createProperty(property);
    }

    @GetMapping("/{pid}")
    public Property getPropertyById(@PathVariable("pid") String pid){
        return propertyService.getPropertyById(pid);
    }

    @GetMapping
    public List<Property> getAllProperties(){
        return propertyService.getAllProperties();
    }

    @PutMapping
    public Property updateProperty(@RequestBody @Valid Property updatedProperty){
        return propertyService.updateProperty(updatedProperty);
    }

    @DeleteMapping("/{pid}")
    public void deleteProperty(@PathVariable("pid") String propertyId){

        propertyService.deleteProperty(propertyId);
    }

    @GetMapping("propertyType/{pt}")
    public List<Property> getPropertiesByType(@PathVariable("pt") String propertyType){
        return propertyService.getPropertiesByType(propertyType);
    }

    @GetMapping("/active")
    public List<Property> getActiveProperties(){
        return propertyService.getActiveProperties();
    }

    @GetMapping("/buyers/{bid}")
    public List<Property> matchPropertiesToBuyer(@PathVariable("bid") String buyerId){
        return propertyService.matchPropertiesToBuyer(buyerId);
    }


}

package com.renault.ggva.domain.model;

import com.renault.ggva.domain.enums.FuelType;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Vehicle {
    private Long id;
    // to do garage
    private Long garageId;
    private String brand;
    private String model;
    private int anneeFabrication;
    private FuelType fuelType;
    @Builder.Default
    private List<Accessory> accessories = new ArrayList<>();
}

package com.renault.ggva.dto.request;

import com.renault.ggva.domain.enums.FuelType;

public record AddVehicleRequest(
        String brand,
        String model,
        Integer anneeFabrication,
        FuelType fuelType
) {}

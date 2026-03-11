package com.renault.ggva.dto.response;

import com.renault.ggva.domain.enums.FuelType;

import java.util.List;

public record VehicleResponse(
        Long id,
        Long garageId,
        String brand,
        String model,
        int anneeFabrication,
        FuelType fuelType,
        List<AccessoryResponse> accessories
) {}
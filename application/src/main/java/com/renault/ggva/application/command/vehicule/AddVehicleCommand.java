package com.renault.ggva.application.command.vehicule;


import com.renault.ggva.domain.enums.FuelType;

import java.util.Objects;

public record AddVehicleCommand(
        Long garageId,
        String brand,
        String model,
        int anneeFabrication,
        FuelType fuelType
) {
    public AddVehicleCommand {
        Objects.requireNonNull(garageId, "GarageId is required");
        Objects.requireNonNull(brand, "Brand is required");
        Objects.requireNonNull(model, "Model is required");
        Objects.requireNonNull(fuelType, "FuelType is required");
    }
}

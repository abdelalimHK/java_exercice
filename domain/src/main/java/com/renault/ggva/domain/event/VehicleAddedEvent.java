package com.renault.ggva.domain.event;

import com.renault.ggva.domain.enums.FuelType;

import java.time.LocalDateTime;

public record VehicleAddedEvent(
        Long vehicleId,
        Long garageId,
        String brand,
        String model,
        FuelType fuelType,
        int anneeFabrication,
        LocalDateTime occurredAt
) implements DomainEvent { }

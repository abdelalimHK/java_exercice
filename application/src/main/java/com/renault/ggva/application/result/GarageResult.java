package com.renault.ggva.application.result;

public record GarageResult(
        Long id,
        String name,
        String address,
        String telephone,
        String email,
        int vehicleCount,
        int availableCapacity
) {}

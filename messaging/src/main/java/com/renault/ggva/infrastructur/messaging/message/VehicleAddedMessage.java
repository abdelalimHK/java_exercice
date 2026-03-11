package com.renault.ggva.infrastructur.messaging.message;

import java.time.LocalDateTime;

public record VehicleAddedMessage(
        Long vehicleId,
        Long garageId,
        String brand,
        String model,
        LocalDateTime occurredAt
) {}

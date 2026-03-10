package com.renault.ggva.application.query;

import com.renault.ggva.domain.enums.AccessoryType;
import com.renault.ggva.domain.enums.FuelType;

public record GarageSearchCriteria(
        String name,
        String city,
        String adress,
        FuelType fuelType,
        AccessoryType accessoryType
) { }

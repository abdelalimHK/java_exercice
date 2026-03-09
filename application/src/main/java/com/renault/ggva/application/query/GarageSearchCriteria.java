package com.renault.ggva.application.query;

import com.renault.ggva.domain.enums.AccessoryType;
import com.renault.ggva.domain.enums.FuelType;

public record GarageSearchCriteria(
        String name,
        String city,
        FuelType fuelType,
        AccessoryType accessoryType
) {
    // static factory for empty criteria — list all
    public static GarageSearchCriteria empty() {
        return new GarageSearchCriteria(null, null, null, null);
    }
}

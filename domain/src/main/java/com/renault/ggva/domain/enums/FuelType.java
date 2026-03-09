package com.renault.ggva.domain.enums;

public enum FuelType {

    ESSENCE("Essence"),
    DIESEL("Diesel"),
    ELECTRIQUE("Électrique"),
    HYBRIDE("Hybride"),
    HYBRIDE_RECHARGEABLE("Hybride Rechargeable");

    private final String label;

    FuelType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

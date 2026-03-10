package com.renault.ggva.domain.enums;

public enum AccessoryType {

    SECURITE("Sécurité"),
    CONFORT("Confort"),
    PERFORMANCE("Performance"),
    ESTHETIQUE("Esthétique"),
    NAVIGATION("Navigation");

    private final String label;

    AccessoryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
